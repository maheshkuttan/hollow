package com.netflix.vms.transformer.hollowinput;

import com.netflix.hollow.api.objects.delegate.HollowObjectDelegate;


@SuppressWarnings("all")
public interface PersonCharacterResourceDelegate extends HollowObjectDelegate {

    public long getId(int ordinal);

    public Long getIdBoxed(int ordinal);

    public int getPrefixOrdinal(int ordinal);

    public int getCnOrdinal(int ordinal);

    public PersonCharacterResourceTypeAPI getTypeAPI();

}