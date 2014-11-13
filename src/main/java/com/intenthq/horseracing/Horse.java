package com.intenthq.horseracing;

public class Horse {
    private final int lane;
    private final String horseName;
    private final int yardsCovered;

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
}
