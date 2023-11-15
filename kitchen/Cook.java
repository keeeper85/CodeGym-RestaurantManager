package com.codegym.task.task27.task2712.kitchen;

import com.codegym.task.task27.task2712.ConsoleHelper;
import com.codegym.task.task27.task2712.Restaurant;
import com.codegym.task.task27.task2712.Tablet;
import com.codegym.task.task27.task2712.statistics.StatisticsManager;
import com.codegym.task.task27.task2712.statistics.event.OrderReadyEventDataRow;

import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.LinkedBlockingQueue;

public class Cook extends Observable implements Runnable {

    private String name;
    private boolean busy;
    private LinkedBlockingQueue<Order> queue;

    public Cook(String name) {
        this.name = name;
        setQueue(Restaurant.getOrderQueue());
    }

    @Override
    public String toString() {
        return name;
    }

    public void startCookingOrder(Order order){
        busy = true;
        ConsoleHelper.writeMessage("Start cooking - " + order);
        StatisticsManager statisticsManager = StatisticsManager.getInstance();
        statisticsManager.record(new OrderReadyEventDataRow(order.getTablet().toString(), this.name, order.getTotalCookingTime(), order.dishes));
        int preparationTimeDelay = order.getTotalCookingTime() * 10;
        try {
            Thread.sleep(preparationTimeDelay);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        busy = false;
    }

    public boolean isBusy() {
        return busy;
    }

    public void setQueue(LinkedBlockingQueue<Order> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true){
            try {
                if (!queue.isEmpty()){
                    this.startCookingOrder(queue.take());
                }
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    //    @Override
//    public void update(Observable o, Object arg) {
//        Order order = (Order) arg;
//
//        setChanged();
//
//
//        notifyObservers(arg);
//    }
}
