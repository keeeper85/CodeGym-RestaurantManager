package com.codegym.task.task27.task2712.statistics.event;

import java.util.Date;

public class NoVideosAvailableEventDataRow implements EventDataRow{

    private Date currentDate;
    private int totalDuration;

    public NoVideosAvailableEventDataRow(int totalDuration){
        currentDate = new Date();
        this.totalDuration = totalDuration;
    }

    @Override
    public EventType getType() {
        return EventType.NO_VIDEOS_AVAILABLE;
    }

    @Override
    public Date getDate() {
        return currentDate;
    }

    @Override
    public int getTime() {
        return totalDuration;
    }
}
