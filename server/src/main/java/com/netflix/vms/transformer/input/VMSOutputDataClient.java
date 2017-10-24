package com.netflix.vms.transformer.input;

import com.netflix.aws.file.FileStore;
import com.netflix.hollow.api.client.HollowClient;
import com.netflix.vms.transformer.util.HollowBlobKeybaseBuilder;

public class VMSOutputDataClient extends HollowClient {
    
    public VMSOutputDataClient(FileStore fileStore, String transformerVip) {
        super(new VMSDataTransitionCreator(fileStore, new HollowBlobKeybaseBuilder(transformerVip)));
    }
    
    public VMSOutputDataClient(String baseProxyURL, String localDataDir, String transformerVip) {
        super(new VMSDataProxyTransitionCreator(baseProxyURL, localDataDir, new HollowBlobKeybaseBuilder(transformerVip)));
    }
    
}