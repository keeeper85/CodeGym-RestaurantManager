package com.codegym.task.task27.task2712;

import com.codegym.task.task27.task2712.kitchen.Dish;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ConsoleHelper{

    private static BufferedReader reader;

    public static void writeMessage(String message){
        System.out.println(message);
    }

    public static String readString() throws IOException {
        reader = new BufferedReader(new InputStreamReader(System.in));

        return reader.readLine();
    }

    public static List<Dish> getAllDishesForOrder() throws IOException {
        writeMessage("What do you want to order? We have: ");
        writeMessage(Dish.allDishesToString());

        List<Dish> orderList = new ArrayList<>();
        while (true){
            String input = readString();
            int count = 0;

            if (input.equals("exit")) break;

            for (Dish values : Dish.values()){
                if (values.toString().equals(input)){
                    orderList.add(values);
                    writeMessage("Excellent choice!");
                    break;
                }
                count++;
                if (count == Dish.values().length) writeMessage("I am sorry, there is no such dish. Please, try again.");
            }
            writeMessage("Anything else? Type 'exit' if you want to complete your order.");
        }


        return orderList;
    }

}
