package com.intenthq.horseracing;

public class Horse {
    public static final int YARDS_IN_FURLONG = 220;
    private final int lane;
    private final String horseName;
    private int yardsCovered;

    public Horse(final int lane, final String horseName) {
        this.lane = lane;
        this.yardsCovered = 0;
        this.horseName = horseName;
    }

    public Horse(final int lane, final String horseName, final int yardsCovered) {
        this.lane = lane;
        this.horseName = horseName;
        this.yardsCovered = yardsCovered;
    }

    public String getHorseName() {
        return horseName;
    }

    public int getYardsCovered() {
        return yardsCovered;
    }

    public int getLane() {
        return lane;
    }

    public void addYards(int yards) {
        this.yardsCovered = yardsCovered + yards;
    }

    public boolean isFinished() {
        return yardsCovered > YARDS_IN_FURLONG;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        Horse rhs = (Horse) obj;
        return new org.apache.commons.lang3.builder.EqualsBuilder()
                .append(this.lane, rhs.lane)
                .append(this.horseName, rhs.horseName)
                .append(this.yardsCovered, rhs.yardsCovered)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new org.apache.commons.lang3.builder.HashCodeBuilder()
                .append(lane)
                .append(horseName)
                .append(yardsCovered)
                .toHashCode();
    }


    @Override
    public String toString() {
        return new org.apache.commons.lang3.builder.ToStringBuilder(this)
                .append("lane", lane)
                .append("horseName", horseName)
                .append("yardsCovered", yardsCovered)
                .toString();
    }
}
