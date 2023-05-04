package com.vector.mill;

import com.vector.mill.bean.Flour;
import com.vector.mill.bean.Millet;
import com.vector.mill.bean.Water;
import com.vector.mill.engine.Engine;
import com.vector.mill.engine.WaterWheel;
import com.vector.mill.machine.Machine;
import com.vector.mill.machine.MillStone;

import java.util.Queue;

public class Mill extends Thread{

    private final Machine machine;
    private final Engine engine;


    public Mill(Queue<Water> waterFlow, Queue<Millet> milletFlow, Queue<Flour> floursFlow) {
        this.engine = new WaterWheel(waterFlow);
        this.engine.start();
        this.machine = new MillStone(engine, milletFlow, floursFlow);
        this.machine.start();
    }

    public boolean isMachineOn(){
        return this.machine.isOn();
    }

    public boolean isEngineOn(){
        return this.engine.getPower() > 0;
    }

    public int getPower(){
        return this.engine.getPower();
    };
    @Override
    public void run() {
        while (!isInterrupted()) {
            if (engine.getPower() > 0) {
                machine.on();
            }
        }
    }
}
