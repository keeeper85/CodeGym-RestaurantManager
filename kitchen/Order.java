package com.codegym.task.task27.task2712.kitchen;

import com.codegym.task.task27.task2712.ConsoleHelper;
import com.codegym.task.task27.task2712.Tablet;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

public class Order {

    private final Tablet tablet;

    protected List<Dish> dishes;

    public Order(Tablet tablet) throws IOException {
        this.tablet = tablet;
        initDishes();
    }

    public int getTotalCookingTime(){

        int totalCookingTime = 0;

        for (Dish dish : dishes) {
            totalCookingTime += dish.getDuration();
        }
        return totalCookingTime;
    }

    public boolean isEmpty(){
        if (dishes.isEmpty()) return true;
        return false;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public Tablet getTablet() {
        return tablet;
    }

    protected void initDishes() throws IOException {
        dishes = ConsoleHelper.getAllDishesForOrder();
    }

    @Override
    public String toString() {
        if (isEmpty()) return "";

        StringBuilder orderedDishes = new StringBuilder("Your order: [");
        int count = 0;
        for (Dish dish : dishes) {
            orderedDishes.append(dish);
            count++;
            if (count < dishes.size()) orderedDishes.append(", ");
            else orderedDishes.append("] from " + tablet.toString());
        }
        return orderedDishes.toString() + ", cooking time " + getTotalCookingTime() + " min";
    }
}
