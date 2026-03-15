package com.appointment.domain;

public class Equipment {

    private String equipmentId;
    private String equipmentName;
    private boolean available;

    public Equipment(String equipmentId, String equipmentName, boolean available) {
        this.equipmentId = equipmentId;
        this.equipmentName = equipmentName;
        this.available = available;
    }

    public String getEquipmentId() { return equipmentId; }
    public String getEquipmentName() { return equipmentName; }
    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }
}