package com.appointment.domain;


public class TimeSlot {

   
    private String slotId;

    
    private String startTime;

   
    private String endTime;

   
    private boolean available;

   
    public TimeSlot(String slotId, String startTime, String endTime) {
        this.slotId    = slotId;
        this.startTime = startTime;
        this.endTime   = endTime;
        this.available = true;
    }

  
    public String getSlotId() { return slotId; }

  
    public String getStartTime() { return startTime; }

        public String getEndTime() { return endTime; }

    
    public boolean isAvailable() { return available; }

    
    public void setAvailable(boolean available) { this.available = available; }

    @Override
    public String toString() {
        return "TimeSlot{" + startTime + " - " + endTime + ", available=" + available + "}";
    }
}