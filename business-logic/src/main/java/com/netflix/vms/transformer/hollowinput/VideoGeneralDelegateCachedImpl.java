package com.netflix.vms.transformer.hollowinput;

import com.netflix.hollow.api.objects.delegate.HollowObjectAbstractDelegate;
import com.netflix.hollow.core.read.dataaccess.HollowObjectTypeDataAccess;
import com.netflix.hollow.core.schema.HollowObjectSchema;
import com.netflix.hollow.api.custom.HollowTypeAPI;
import com.netflix.hollow.api.objects.delegate.HollowCachedDelegate;

@SuppressWarnings("all")
public class VideoGeneralDelegateCachedImpl extends HollowObjectAbstractDelegate implements HollowCachedDelegate, VideoGeneralDelegate {

    private final Long videoId;
    private final Boolean tv;
    private final int aliasesOrdinal;
    private final int videoTypeOrdinal;
    private final Long runtime;
    private final int supplementalSubTypeOrdinal;
    private final Long firstReleaseYear;
    private final Boolean testTitle;
    private final int originalLanguageBcpCodeOrdinal;
    private final Integer metadataReleaseDays;
    private final int originCountryCodeOrdinal;
    private final int originalTitleOrdinal;
    private final int testTitleTypesOrdinal;
    private final int originalTitleBcpCodeOrdinal;
    private final int internalTitleOrdinal;
    private final int episodeTypesOrdinal;
    private final int regulatoryAdvisoriesOrdinal;
    private VideoGeneralTypeAPI typeAPI;

    public VideoGeneralDelegateCachedImpl(VideoGeneralTypeAPI typeAPI, int ordinal) {
        this.videoId = typeAPI.getVideoIdBoxed(ordinal);
        this.tv = typeAPI.getTvBoxed(ordinal);
        this.aliasesOrdinal = typeAPI.getAliasesOrdinal(ordinal);
        this.videoTypeOrdinal = typeAPI.getVideoTypeOrdinal(ordinal);
        this.runtime = typeAPI.getRuntimeBoxed(ordinal);
        this.supplementalSubTypeOrdinal = typeAPI.getSupplementalSubTypeOrdinal(ordinal);
        this.firstReleaseYear = typeAPI.getFirstReleaseYearBoxed(ordinal);
        this.testTitle = typeAPI.getTestTitleBoxed(ordinal);
        this.originalLanguageBcpCodeOrdinal = typeAPI.getOriginalLanguageBcpCodeOrdinal(ordinal);
        this.metadataReleaseDays = typeAPI.getMetadataReleaseDaysBoxed(ordinal);
        this.originCountryCodeOrdinal = typeAPI.getOriginCountryCodeOrdinal(ordinal);
        this.originalTitleOrdinal = typeAPI.getOriginalTitleOrdinal(ordinal);
        this.testTitleTypesOrdinal = typeAPI.getTestTitleTypesOrdinal(ordinal);
        this.originalTitleBcpCodeOrdinal = typeAPI.getOriginalTitleBcpCodeOrdinal(ordinal);
        this.internalTitleOrdinal = typeAPI.getInternalTitleOrdinal(ordinal);
        this.episodeTypesOrdinal = typeAPI.getEpisodeTypesOrdinal(ordinal);
        this.regulatoryAdvisoriesOrdinal = typeAPI.getRegulatoryAdvisoriesOrdinal(ordinal);
        this.typeAPI = typeAPI;
    }

    public long getVideoId(int ordinal) {
        if(videoId == null)
            return Long.MIN_VALUE;
        return videoId.longValue();
    }

    public Long getVideoIdBoxed(int ordinal) {
        return videoId;
    }

    public boolean getTv(int ordinal) {
        if(tv == null)
            return false;
        return tv.booleanValue();
    }

    public Boolean getTvBoxed(int ordinal) {
        return tv;
    }

    public int getAliasesOrdinal(int ordinal) {
        return aliasesOrdinal;
    }

    public int getVideoTypeOrdinal(int ordinal) {
        return videoTypeOrdinal;
    }

    public long getRuntime(int ordinal) {
        if(runtime == null)
            return Long.MIN_VALUE;
        return runtime.longValue();
    }

    public Long getRuntimeBoxed(int ordinal) {
        return runtime;
    }

    public int getSupplementalSubTypeOrdinal(int ordinal) {
        return supplementalSubTypeOrdinal;
    }

    public long getFirstReleaseYear(int ordinal) {
        if(firstReleaseYear == null)
            return Long.MIN_VALUE;
        return firstReleaseYear.longValue();
    }

    public Long getFirstReleaseYearBoxed(int ordinal) {
        return firstReleaseYear;
    }

    public boolean getTestTitle(int ordinal) {
        if(testTitle == null)
            return false;
        return testTitle.booleanValue();
    }

    public Boolean getTestTitleBoxed(int ordinal) {
        return testTitle;
    }

    public int getOriginalLanguageBcpCodeOrdinal(int ordinal) {
        return originalLanguageBcpCodeOrdinal;
    }

    public int getMetadataReleaseDays(int ordinal) {
        if(metadataReleaseDays == null)
            return Integer.MIN_VALUE;
        return metadataReleaseDays.intValue();
    }

    public Integer getMetadataReleaseDaysBoxed(int ordinal) {
        return metadataReleaseDays;
    }

    public int getOriginCountryCodeOrdinal(int ordinal) {
        return originCountryCodeOrdinal;
    }

    public int getOriginalTitleOrdinal(int ordinal) {
        return originalTitleOrdinal;
    }

    public int getTestTitleTypesOrdinal(int ordinal) {
        return testTitleTypesOrdinal;
    }

    public int getOriginalTitleBcpCodeOrdinal(int ordinal) {
        return originalTitleBcpCodeOrdinal;
    }

    public int getInternalTitleOrdinal(int ordinal) {
        return internalTitleOrdinal;
    }

    public int getEpisodeTypesOrdinal(int ordinal) {
        return episodeTypesOrdinal;
    }

    public int getRegulatoryAdvisoriesOrdinal(int ordinal) {
        return regulatoryAdvisoriesOrdinal;
    }

    @Override
    public HollowObjectSchema getSchema() {
        return typeAPI.getTypeDataAccess().getSchema();
    }

    @Override
    public HollowObjectTypeDataAccess getTypeDataAccess() {
        return typeAPI.getTypeDataAccess();
    }

    public VideoGeneralTypeAPI getTypeAPI() {
        return typeAPI;
    }

    public void updateTypeAPI(HollowTypeAPI typeAPI) {
        this.typeAPI = (VideoGeneralTypeAPI) typeAPI;
    }

}