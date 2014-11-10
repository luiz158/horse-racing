package com.intenthq.horseracing.entities;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
* Created by antmyth on 10/11/14.
*/
public class Play {
    private final int lane;
    private final int yards;

    public Play(int lane, int yards) {
        this.lane = lane;
        this.yards = yards;
    }

    public int lane() {
        return lane;
    }

    public int yards() {
        return yards;
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object o) {
        return EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
