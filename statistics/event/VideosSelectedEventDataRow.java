package com.codegym.task.task27.task2712.statistics.event;

import com.codegym.task.task27.task2712.ad.Advertisement;

import java.util.Date;
import java.util.List;

public class VideosSelectedEventDataRow implements EventDataRow{

    private Date currentDate;
    private List optimalVideoSet;
    private long amount;
    private int totalDuration;

    public VideosSelectedEventDataRow(List optimalVideoSet, long amount, int totalDuration) {
        this.optimalVideoSet = optimalVideoSet;
        this.amount = amount;
        this.totalDuration = totalDuration;
        currentDate = new Date();
    }

    @Override
    public EventType getType() {
        return EventType.VIDEOS_SELECTED;
    }

    @Override
    public Date getDate() {
        return currentDate;
    }

    @Override
    public int getTime() {
        return totalDuration;
    }

    public long getAmount() {
        return amount;
    }
}
