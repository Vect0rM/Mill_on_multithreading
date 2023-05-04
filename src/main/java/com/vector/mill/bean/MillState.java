package com.vector.mill.bean;

import com.vector.mill.Mill;

import java.util.Queue;

public class MillState {
    private final int water;
    private final int millet;
    private final int flour;
    private final int power;

    private final boolean engine;
    private final boolean machine;

    public MillState(Mill mill, Queue<Water> waters, Queue<Millet> millets, Queue<Flour> flours) {
        this.water = waters.size();
        this.millet = millets.size();
        this.flour = flours.size();

        power = mill.getPower();
        engine = mill.isEngineOn();
        machine = mill.isMachineOn();
    }
}
