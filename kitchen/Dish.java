package com.codegym.task.task27.task2712.kitchen;

public enum Dish {

    Fish(25),
    Steak(30),
    Soup(15),
    Juice(5),
    Water(3);


    private int duration;

    Dish(int duration) {
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }

    public static String allDishesToString(){

        StringBuilder dishChoice = new StringBuilder();
        int count = 0;
        for (Dish dish : Dish.values()) {
            dishChoice.append(dish);
            count++;
            if (count < Dish.values().length) dishChoice.append(", ");
            else dishChoice.append(".");
        }
        return dishChoice.toString();
    }

}
