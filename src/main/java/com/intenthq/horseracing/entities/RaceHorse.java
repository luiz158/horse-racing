package com.intenthq.horseracing.entities;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class RaceHorse {
    private final int lane;
    private final String name;
    private int positionOnTrack;

    public RaceHorse(int lane, String name) {
        this.lane = lane;
        this.name = name;
        positionOnTrack = 0;
    }

    public int lane() {
        return lane;
    }

    public String name() {
        return name;
    }

    public int positionOnTrack() {
        return positionOnTrack;
    }

    public void advance(int yardsToMove) {
        positionOnTrack += yardsToMove;
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
