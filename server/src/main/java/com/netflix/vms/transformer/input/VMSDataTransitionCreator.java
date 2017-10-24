package com.netflix.vms.transformer.input;

import com.netflix.aws.file.FileAccessItem;
import com.netflix.aws.file.FileStore;
import com.netflix.hollow.api.client.HollowBlob;
import com.netflix.hollow.api.client.HollowBlobRetriever;
import com.netflix.vms.transformer.common.KeybaseBuilder;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VMSDataTransitionCreator implements HollowBlobRetriever {

    private static final Logger LOGGER = LoggerFactory.getLogger(VMSDataTransitionCreator.class);

    private final FileStore fileStore;
    private final KeybaseBuilder keybaseBuilder;

    public VMSDataTransitionCreator(FileStore fileStore, String converterVip) {
        this(fileStore, new VMSInputDataKeybaseBuilder(converterVip));
    }
    
    public VMSDataTransitionCreator(FileStore fileStore, KeybaseBuilder keybaseBuilder) {
        this.fileStore = fileStore;
        this.keybaseBuilder = keybaseBuilder;
    }

    @Override
    public HollowBlob retrieveSnapshotBlob(long desiredVersion) {
        TransitionResult result = new TransitionResult();
        String snapshotKeybase = keybaseBuilder.getSnapshotKeybase();

        int retryCount = 0;
        while(retryCount < 3) {
            retryCount++;

            try {
                FileAccessItem latestItem = fileStore.getPublishedFileAccessItem(snapshotKeybase);
                if(latestItem != null) {
                    long version = FileStoreUtil.getToVersion(latestItem);

                    if(version <= desiredVersion)
                        return createTransition(latestItem);
                    else
                        break;
                }
            } catch(Exception e) { }

        }

        if(result.transition != null)
            return result.transition;

        retryCount = 0;
        while(retryCount < 3) {
            retryCount++;
            try {
                List<FileAccessItem> allFileAccessItems = fileStore.getAllFileAccessItems(snapshotKeybase);

                Collections.sort(allFileAccessItems, new Comparator<FileAccessItem>() {
                    public int compare(FileAccessItem o1, FileAccessItem o2) {
                        long toVersion1 = FileStoreUtil.getToVersion(o1);
                        long toVersion2 = FileStoreUtil.getToVersion(o2);

                        if(toVersion1 < toVersion2)
                            return 1;
                        if(toVersion1 > toVersion2)
                            return -1;
                        return 0;
                    }
                });

                for(FileAccessItem item : allFileAccessItems) {
                    long toVersion = FileStoreUtil.getToVersion(item);
                    if(toVersion <= desiredVersion) {
                        return createTransition(item);
                    }
                }

                break;

            } catch(Exception e) {
                LOGGER.error("Failed to find snapshot transition", e);
            }
        }

        return null;
    }

    @Override
    public HollowBlob retrieveDeltaBlob(long currentVersion) {
        int retryCount = 0;
        while(retryCount < 3) {
            retryCount++;
            try {
                FileAccessItem fileAccessItem = fileStore.getPublishedFileAccessItem(keybaseBuilder.getDeltaKeybase(), String.valueOf(currentVersion));
                if(fileAccessItem == null)
                    return null;

                return createTransition(fileAccessItem);
            } catch(Exception e) {
                LOGGER.error("Failed to find delta transition", e);
            }
        }

        return null;
    }

    @Override
    public HollowBlob retrieveReverseDeltaBlob(long currentVersion) {
        return null;
    }

    private FileStoreHollowUpdateTransition createTransition(FileAccessItem latestItem) {
        FileStoreHollowUpdateTransition transition = new FileStoreHollowUpdateTransition(latestItem, fileStore);
        return transition;
    }

    private class TransitionResult {
        HollowBlob transition = null;
    }

}