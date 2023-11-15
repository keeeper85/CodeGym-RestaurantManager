package com.codegym.task.task27.task2712.statistics;

import com.codegym.task.task27.task2712.kitchen.Cook;
import com.codegym.task.task27.task2712.statistics.event.EventDataRow;
import com.codegym.task.task27.task2712.statistics.event.EventType;
import com.codegym.task.task27.task2712.statistics.event.OrderReadyEventDataRow;
import com.codegym.task.task27.task2712.statistics.event.VideosSelectedEventDataRow;

import java.util.*;

public class StatisticsManager {

    private static StatisticsManager statisticsManager = new StatisticsManager();
    private StatisticsStorage statisticsStorage = new StatisticsStorage();
//    private Set<Cook> cooks = new HashSet<>();
    private HashMap<Date, TreeMap<String, Long>> cookHourPerDay =  new HashMap<Date, TreeMap<String, Long>>();
    private TreeMap<Date, Double> revenuePerDays =  new TreeMap<Date, Double>();

    private StatisticsManager() {
    }

    public static StatisticsManager getInstance() {
        return statisticsManager;
    }

    public void record(EventDataRow data) {
        statisticsStorage.put(data);
    }

//    public void register(Cook cook) {
//        cooks.add(cook);
//    }

    public TreeMap<Date, Double> getRevenuePerDays(){
        for(Map.Entry<EventType, List<EventDataRow>> e : statisticsStorage.getStorage().entrySet()){
            if(e.getKey() == EventType.VIDEOS_SELECTED)
                for(EventDataRow x: e.getValue()){
                    VideosSelectedEventDataRow videosSelectedEventDataRow = (VideosSelectedEventDataRow) x;
                    Date day = new Date(videosSelectedEventDataRow.getDate().getYear(), videosSelectedEventDataRow.getDate().getMonth(), videosSelectedEventDataRow.getDate().getDate());
                    Double result = revenuePerDays.get(day);
                    if(result == null)
                        result = (double) 0;
                    revenuePerDays.put(day, result + videosSelectedEventDataRow.getAmount());

                }
        }
        return revenuePerDays;
    }

    public HashMap<Date, TreeMap<String, Long>> getCookUtilization(){
        for(Map.Entry<EventType, List<EventDataRow>> e : statisticsStorage.getStorage().entrySet()) {
            if (e.getKey() == EventType.ORDER_READY) {
                for(EventDataRow x: e.getValue()) {
                    //String tabletName, String cookName, int cookingTimeSeconds, List<Dish > dishesInOrder
                    OrderReadyEventDataRow orderReadyEventDataRow = (OrderReadyEventDataRow) x;
                    Date day = new Date(orderReadyEventDataRow.getDate().getYear(), orderReadyEventDataRow.getDate().getMonth(), orderReadyEventDataRow.getDate().getDate());
                    TreeMap<String, Long> cookList = cookHourPerDay.get(day);
                    if(cookList == null)
                        cookList = new TreeMap<String, Long>();
                    Long cookTime = cookList.get(orderReadyEventDataRow.getCookName());
                    if( cookTime == null)
                        cookTime = 0l;
                    cookTime += (long) orderReadyEventDataRow.getCookingTimeSeconds();

                    cookList.put(orderReadyEventDataRow.getCookName(),cookTime);
                    cookHourPerDay.put(day,cookList);
                }
            }
        }
        return cookHourPerDay;
    }

//    public Set<Cook> getCooks() {
//        return cooks;
//    }

    private class StatisticsStorage{
        private Map<EventType, List<EventDataRow>> storage = new HashMap<>();

        public StatisticsStorage() {
            for (EventType value : EventType.values()) {
                storage.put(value, new ArrayList<EventDataRow>());
            }
        }

        private void put(EventDataRow data){
            List<EventDataRow> list = storage.get(data.getType());
            list.add(data);
            storage.put(data.getType(), list);
        }

        private Map<EventType, List<EventDataRow>> getStorage(){
            return storage;
        }
    }
}
