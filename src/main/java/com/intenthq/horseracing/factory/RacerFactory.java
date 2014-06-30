package com.intenthq.horseracing.factory;

import com.intenthq.horseracing.model.Racer;
import com.intenthq.horseracing.model.HorseRacer;

public class RacerFactory {

    public static Racer createNewHorseRacer(String name) {
        
        HorseRacer horseRacer = new HorseRacer();
        horseRacer.setName(name);
        
        return horseRacer;
    }
}
