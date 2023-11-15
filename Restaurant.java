package com.codegym.task.task27.task2712;

import com.codegym.task.task27.task2712.ad.Advertisement;
import com.codegym.task.task27.task2712.ad.AdvertisementManager;
import com.codegym.task.task27.task2712.ad.AdvertisementStorage;
import com.codegym.task.task27.task2712.kitchen.Cook;
import com.codegym.task.task27.task2712.kitchen.Dish;
import com.codegym.task.task27.task2712.kitchen.Order;
import com.codegym.task.task27.task2712.kitchen.Waiter;
import com.codegym.task.task27.task2712.statistics.StatisticsManager;
import com.codegym.task.task27.task2712.statistics.event.EventDataRow;
import com.codegym.task.task27.task2712.statistics.event.OrderReadyEventDataRow;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.LinkedBlockingQueue;

public class Restaurant implements Observer {

    private static final int ORDER_CREATION_INTERVAL = 100;
    private static final LinkedBlockingQueue<Order> orderQueue = new LinkedBlockingQueue<>();

    public static LinkedBlockingQueue<Order> getOrderQueue() {
        return orderQueue;
    }



    public static void main(String[] args) {


        Cook cook1 = new Cook("Amigo");
        Cook cook2 = new Cook("Pierre");
        StatisticsManager statisticsManager = StatisticsManager.getInstance();


        List<Tablet> tablets = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            tablets.add(new Tablet(i));
        }



//        for (Tablet tablet : tablets) {
//            tablet.addObserver(orderManager);
//            tablet.addObserver(orderManager);
//        }

        Waiter waiter = new Waiter();

//        orderManager.addObserver(waiter);
        cook1.addObserver(waiter);
        cook2.addObserver(waiter);

        Thread thread = new Thread(new RandomOrderGeneratorTask(tablets, ORDER_CREATION_INTERVAL));
        thread.start();

        Thread cookThread = new Thread (cook1);
        cookThread.start();




//            tablet.addObserver(cook);
//            cook.addObserver(waiter);

//            tablet.createTestOrder();



//            tablet.createOrder();
//            tablet.createOrder();
//            tablet.createOrder();

//        ManagerTablet managerTablet = new ManagerTablet();
//        managerTablet.printAdRevenue();
//        managerTablet.printCookUtilization();
//        managerTablet.printActiveVideoSet();
//        managerTablet.printArchivedVideoSet();
////        StatisticsManager statisticsManager = StatisticsManager.getInstance();
////        for (EventDataRow arg : statisticsManager.getAllRepositoryDataForCooks()) {
////            System.out.println(arg.getType() + " " + arg.getDate() + " " + arg.getTime());
////        }
////        managerTablet.printCookUtilization();

    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
