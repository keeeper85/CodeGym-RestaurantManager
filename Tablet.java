package com.codegym.task.task27.task2712;

import com.codegym.task.task27.task2712.ad.AdvertisementManager;
import com.codegym.task.task27.task2712.ad.NoVideoAvailableException;
import com.codegym.task.task27.task2712.kitchen.Cook;
import com.codegym.task.task27.task2712.kitchen.Order;
import com.codegym.task.task27.task2712.kitchen.TestOrder;
import com.codegym.task.task27.task2712.statistics.event.OrderReadyEventDataRow;

import java.io.IOException;
import java.io.ObjectStreamClass;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Tablet {

    private final int number;
    private static List<Tablet> listOfAllTablets = new ArrayList<>();
    private Order order = null;

    private LinkedBlockingQueue<Order> queue;
    private static java.util.logging.Logger logger = Logger.getLogger(Tablet.class.getName());

    public Tablet(int number) {
        this.number = number;
        listOfAllTablets.add(this);

    }

    public Order createOrder() {

        try {
            order = new Order(this);
//            setChanged();
//            notifyObservers(order);
            Restaurant.getOrderQueue().add(order);

            AdvertisementManager advertisementManager = new AdvertisementManager(order.getTotalCookingTime() * 60);
            advertisementManager.processVideos();

            return order;
        } catch (IOException e) {
            logger.log(Level.SEVERE, "The console is unavailable.");
            return null;
        } catch (NoVideoAvailableException e){
            logger.log(Level.INFO, "No video is available for the following order: " + order.toString());
        }
        return order;
    }

    public void createTestOrder() {
        TestOrder order;
        try {
            order = new TestOrder(this);
            processOrder(order);
        } catch (IOException e) {}
    }

    private void processOrder(Order order) {
//        setChanged();
//        notifyObservers(order);
        Restaurant.getOrderQueue().add(order);
        int seconds = order.getTotalCookingTime() * 60;
        AdvertisementManager manager = new AdvertisementManager(seconds);
        manager.processVideos();
    }


    public static List<Tablet> getListOfAllTablets() {
        return listOfAllTablets;
    }

    @Override
    public String toString() {
        return "Tablet{" +
                "number=" + number +
                '}';
    }
}
