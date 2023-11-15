package com.codegym.task.task27.task2712;

import com.codegym.task.task27.task2712.ad.Advertisement;
import com.codegym.task.task27.task2712.ad.StatisticsAdvertisementManager;
import com.codegym.task.task27.task2712.kitchen.Cook;
import com.codegym.task.task27.task2712.statistics.StatisticsManager;
import com.codegym.task.task27.task2712.statistics.event.EventDataRow;
import com.codegym.task.task27.task2712.statistics.event.OrderReadyEventDataRow;
import com.codegym.task.task27.task2712.statistics.event.VideosSelectedEventDataRow;

import javax.xml.crypto.Data;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

public class ManagerTablet {

    private StatisticsManager statisticsManager = StatisticsManager.getInstance();

    private StatisticsAdvertisementManager statisticsAdvertisementManager = StatisticsAdvertisementManager.getInstance();
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);

    public void printAdRevenue(){
        StatisticsManager statisticsManager = StatisticsManager.getInstance();
        Double total = (double)0;

        for(Map.Entry<Date, Double> revenuePerDay : statisticsManager.getRevenuePerDays().descendingMap().entrySet()){
            ConsoleHelper.writeMessage(simpleDateFormat.format(revenuePerDay.getKey()) + " - " + String.format("%.2f",revenuePerDay.getValue() / 100));
            total += revenuePerDay.getValue();
        }


        ConsoleHelper.writeMessage("Total - " + String.format("%.2f",total / 100));
    }

    public void printCookUtilization(){
        StatisticsManager statisticsManager = StatisticsManager.getInstance();
        for(Map.Entry<Date, TreeMap<String, Long>> cookingPerDay : statisticsManager.getCookUtilization().entrySet()){
            ConsoleHelper.writeMessage(simpleDateFormat.format(cookingPerDay.getKey()));
            for(Map.Entry<String, Long> timePerCook : cookingPerDay.getValue().entrySet()){
                ConsoleHelper.writeMessage(timePerCook.getKey() + " - " + timePerCook.getValue() + " min");
            }
            ConsoleHelper.writeMessage("");
        }
    }
    public void printActiveVideoSet(){

        for (Advertisement ads : statisticsAdvertisementManager.getListOfActiveAdvertisement()){
            ConsoleHelper.writeMessage(ads.getName() + " - " + ads.getImpressionsRemaining());
        }

    }
    public void printArchivedVideoSet(){

        List<String> alphabeticalList = new ArrayList<>();
        for (Advertisement ads : statisticsAdvertisementManager.getListOfInactiveAdvertisement()){
            alphabeticalList.add(ads.getName());
        }

        Collections.sort(alphabeticalList, String.CASE_INSENSITIVE_ORDER);

        for (String newList : alphabeticalList){
            ConsoleHelper.writeMessage(newList);
        }

    }

    private class RepositoryDataForCooksHelper{
        private String date;
        private String cooksName;
        private Integer cookingTime;

        public RepositoryDataForCooksHelper(String date, String cooksName, Integer cookingTime) {
            this.date = date;
            this.cooksName = cooksName;
            this.cookingTime = cookingTime;
        }

    }
}
