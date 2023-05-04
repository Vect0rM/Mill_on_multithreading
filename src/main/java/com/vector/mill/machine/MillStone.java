package com.vector.mill.machine;

import com.vector.mill.bean.Flour;
import com.vector.mill.bean.Millet;
import com.vector.mill.engine.Engine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MillStone extends Machine {

    private final Logger logger = LoggerFactory.getLogger(MillStone.class);

    private final Engine engine;
    private final Queue<Millet> mallets;
    private final Queue<Flour> flours;

    private final ExecutorService executorService = Executors.newFixedThreadPool(1);

    public MillStone(Engine engine, Queue<Millet> mallets, Queue<Flour> flours) {
        this.engine = engine;
        this.mallets = mallets;
        this.flours = flours;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            if (engine.getPower() > 0) {
                engine.decPower(1);
                try {
                    this.executorService.submit(() -> {
                        Millet millet = mallets.poll();
                        if (millet != null) {
                            flours.offer(new Flour());
                            logger.info("Flour: " + flours.size());
                        }
                        logger.info("Power left: " + engine.getPower());
                    });
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    logger.error(e.getMessage());
                }
            }
        }
    }
}
