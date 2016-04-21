package com.netflix.vms.transformer.util;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.Collectors.toSet;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;

import com.netflix.hollow.util.HollowObjectHashCodeFinder;
import com.netflix.vms.transformer.hollowoutput.ArtWorkImageTypeEntry;
import com.netflix.vms.transformer.hollowoutput.DrmKeyString;
import com.netflix.vms.transformer.hollowoutput.Episode;
import com.netflix.vms.transformer.hollowoutput.ISOCountry;
import com.netflix.vms.transformer.hollowoutput.NFLocale;
import com.netflix.vms.transformer.hollowoutput.Strings;
import com.netflix.vms.transformer.hollowoutput.SupplementalInfoType;
import com.netflix.vms.transformer.hollowoutput.TrickPlayType;
import com.netflix.vms.transformer.hollowoutput.VPerson;
import com.netflix.vms.transformer.hollowoutput.Video;
import com.netflix.vms.transformer.hollowoutput.VideoFormatDescriptor;
import com.netflix.vms.transformer.hollowoutput.VideoSetType;

public class VMSTransformerHashCodeFinder implements HollowObjectHashCodeFinder {

    static enum RecordType {
        ArtWorkImageTypeEntry,
        DrmKeyString,
        Episode,
        ISOCountry,
        Integer,
        Long,
        NFLocale,
        Strings,
        SupplementalInfoType,
        TrickPlayType,
        VPerson,
        Video,
        VideoFormatDescriptor,
        VideoSetType;

        private static final Map<String, RecordType> lookup =
                Arrays.stream(values()).collect(toMap(RecordType::name, identity()));

        static RecordType lookup(String name) {
            return lookup.get(name);
        }
    }

    public VMSTransformerHashCodeFinder() {}

    @Override
    public int hashCode(String typeName, int ordinal, Object objectToHash) {
        RecordType recordType = RecordType.lookup(typeName);

        if (recordType == null)
            return ordinal;

        switch(recordType) {
        case DrmKeyString:
            return new String(((DrmKeyString)objectToHash).value).hashCode();
        case Episode:
            return ((Episode)objectToHash).id;
        case Integer:
            return ((com.netflix.vms.transformer.hollowoutput.Integer)objectToHash).val;
        case ISOCountry:
            return new String(((ISOCountry)objectToHash).id).hashCode();
        case Long:
            return Long.hashCode(((com.netflix.vms.transformer.hollowoutput.Long)objectToHash).val);
        case NFLocale:
            return new String(((NFLocale)objectToHash).value).hashCode();
        case Strings:
            return new String(((Strings)objectToHash).value).hashCode();
        case SupplementalInfoType:
            return new String(((SupplementalInfoType)objectToHash).value).hashCode();
        case TrickPlayType:
            return new String(((TrickPlayType)objectToHash).value).hashCode();
        case VPerson:
            return ((VPerson)objectToHash).id;
        case Video:
            return ((Video)objectToHash).hashCode();
        case VideoFormatDescriptor:
            return ((VideoFormatDescriptor)objectToHash).id;
        case VideoSetType:
            return new String(((VideoSetType)objectToHash).value).hashCode();
        case ArtWorkImageTypeEntry:
            return new String(((ArtWorkImageTypeEntry)objectToHash).nameStr).hashCode();
        default:
            throw new IllegalArgumentException();
        }
    }

    @Override
    public int hashCode(Object objectToHash) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<String> getTypesWithDefinedHashCodes() {
        return Arrays.stream(RecordType.values()).map(t -> t.name()).collect(toSet());
    }

    @Deprecated
    @Override
    public int hashCode(int ordinal, Object objectToHash) {
        throw new UnsupportedOperationException();
    }

}
