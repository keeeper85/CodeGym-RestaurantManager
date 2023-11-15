package com.codegym.task.task27.task2712;

import com.codegym.task.task27.task2712.kitchen.Cook;
import com.codegym.task.task27.task2712.kitchen.Waiter;

import java.util.ArrayList;
import java.util.List;

public class RandomOrderGeneratorTask implements Runnable {
    private int interval;
//    private Cook cook;
    private List<Tablet> tablets;

    public RandomOrderGeneratorTask(List<Tablet> tablets, int interval) {
        this.tablets = tablets;
        this.interval = interval;
    }

    @Override
    public void run() {
        try {
            int min = 0, max = 3;
            int random = (int) (Math.random() * (tablets.size() - 1));

            while (!Thread.currentThread().isInterrupted()) {
                Tablet tablet = tablets.get(random);
                tablet.createTestOrder();
                Thread.sleep(interval);
            }
        } catch (InterruptedException e) { }
    }
}
