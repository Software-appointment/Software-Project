package com.appointment.domain;

public class CateringService {

    private String cateringId;
    private String serviceType;
    private double price;

    public CateringService(String cateringId, String serviceType, double price) {
        this.cateringId = cateringId;
        this.serviceType = serviceType;
        this.price = price;
    }

    public String getCateringId() { return cateringId; }
    public String getServiceType() { return serviceType; }
    public double getPrice() { return price; }
}