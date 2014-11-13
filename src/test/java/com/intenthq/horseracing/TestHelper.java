package com.intenthq.horseracing;

import com.google.common.collect.Maps;

import java.util.Map;

public class TestHelper {

    public static Map.Entry<Integer, Horse> createHorseEntry(int lane, String horseName, int yardsCovered) {
        return Maps.immutableEntry(new Integer(lane), new Horse(lane, horseName, yardsCovered));
    }

    public static Map.Entry<Integer, Horse> createHorseEntry(int lane, Horse horse) {
        return Maps.immutableEntry(new Integer(lane), horse);
    }
}
