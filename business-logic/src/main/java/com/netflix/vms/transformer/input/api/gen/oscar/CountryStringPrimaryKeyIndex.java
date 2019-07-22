package com.netflix.vms.transformer.input.api.gen.oscar;

import com.netflix.hollow.api.consumer.HollowConsumer;
import com.netflix.hollow.api.consumer.index.AbstractHollowUniqueKeyIndex;
import com.netflix.hollow.api.consumer.index.HollowUniqueKeyIndex;
import com.netflix.hollow.core.schema.HollowObjectSchema;

/**
 * @deprecated see {@link com.netflix.hollow.api.consumer.index.UniqueKeyIndex} which can be built as follows:
 * <pre>{@code
 *     UniqueKeyIndex<CountryString, K> uki = UniqueKeyIndex.from(consumer, CountryString.class)
 *         .usingBean(k);
 *     CountryString m = uki.findMatch(k);
 * }</pre>
 * where {@code K} is a class declaring key field paths members, annotated with
 * {@link com.netflix.hollow.api.consumer.index.FieldPath}, and {@code k} is an instance of
 * {@code K} that is the key to find the unique {@code CountryString} object.
 */
@Deprecated
@SuppressWarnings("all")
public class CountryStringPrimaryKeyIndex extends AbstractHollowUniqueKeyIndex<OscarAPI, CountryString> implements HollowUniqueKeyIndex<CountryString> {

    public CountryStringPrimaryKeyIndex(HollowConsumer consumer) {
        this(consumer, true);
    }

    public CountryStringPrimaryKeyIndex(HollowConsumer consumer, boolean isListenToDataRefresh) {
        this(consumer, isListenToDataRefresh, ((HollowObjectSchema)consumer.getStateEngine().getNonNullSchema("CountryString")).getPrimaryKey().getFieldPaths());
    }

    public CountryStringPrimaryKeyIndex(HollowConsumer consumer, String... fieldPaths) {
        this(consumer, true, fieldPaths);
    }

    public CountryStringPrimaryKeyIndex(HollowConsumer consumer, boolean isListenToDataRefresh, String... fieldPaths) {
        super(consumer, "CountryString", isListenToDataRefresh, fieldPaths);
    }

    @Override
    public CountryString findMatch(Object... keys) {
        int ordinal = idx.getMatchingOrdinal(keys);
        if(ordinal == -1)
            return null;
        return api.getCountryString(ordinal);
    }

}