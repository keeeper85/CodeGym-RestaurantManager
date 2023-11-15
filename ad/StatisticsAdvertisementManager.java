package com.codegym.task.task27.task2712.ad;

import java.util.ArrayList;
import java.util.List;

public class StatisticsAdvertisementManager {

    private static StatisticsAdvertisementManager statisticsAdvertisementManager = new StatisticsAdvertisementManager();
    private AdvertisementStorage advertisementStorage = AdvertisementStorage.getInstance();

    private StatisticsAdvertisementManager() {
    }

    public static StatisticsAdvertisementManager getInstance(){
        return statisticsAdvertisementManager;
    }

    public List<Advertisement> getListOfActiveAdvertisement(){
        List<Advertisement> activeAds = new ArrayList<>();

        for (Advertisement ads : advertisementStorage.list()){
            if (ads.isActive()) activeAds.add(ads);
        }

        return activeAds;
    }

    public List<Advertisement> getListOfInactiveAdvertisement(){
        List<Advertisement> inactiveAds = new ArrayList<>();

        for (Advertisement ads : advertisementStorage.list()){
            if (!ads.isActive()) inactiveAds.add(ads);
        }

        return inactiveAds;
    }
}
