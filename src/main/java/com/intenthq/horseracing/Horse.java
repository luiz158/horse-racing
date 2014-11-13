package com.intenthq.horseracing;

public class Horse {
    private final String horseName;
    private final int yardsCovered;

    public Horse(final String horseName) {
        this.yardsCovered = 0;
        this.horseName = horseName;
    }

    public Horse(final String horseName, final int yardsCovered) {
        this.horseName = horseName;
        this.yardsCovered = yardsCovered;
    }

    public String getHorseName() {
        return horseName;
    }

    public int getYardsCovered() {
        return yardsCovered;
    }
}
