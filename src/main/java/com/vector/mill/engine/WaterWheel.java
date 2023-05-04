package com.vector.mill.engine;

import com.vector.mill.bean.Water;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class WaterWheel extends Engine {

    private final Logger logger = LoggerFactory.getLogger(WaterWheel.class);

    private final Queue<Water> waterFlow;

    private static int MAX_POWER = 10;

    private final ExecutorService executorService = Executors.newFixedThreadPool(2);

    public WaterWheel(Queue<Water> waterFlow) {
        setName("Tread.WaterWheel");
        this.waterFlow = waterFlow;
    }

    @Override
    public void run() {
        while (!isInterrupted()){
            Water water = waterFlow.poll();
            this.executorService.execute(() -> {
                if (water != null && this.getPower() < 10) {
                    this.incPower(water.getPower());
                }
            });
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                logger.error(e.getMessage());
            }
        }
    }
}
