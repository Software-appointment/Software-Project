package com.appointment.domain;

/**
 * Represents an optional catering service available during meetings.
 *
 * @author Student B
 * @version 1.0
 */
public class CateringService {

    /** Unique identifier for the catering service. */
    private String cateringId;

    /** Type of catering service (e.g. Coffee Break, Lunch). */
    private String serviceType;

    /** Price of the catering service. */
    private double price;

    /**
     * Constructor to create a new CateringService.
     *
     * @param cateringId unique ID for the catering service
     * @param serviceType the type of catering service
     * @param price the price of the service
     */
    public CateringService(String cateringId, String serviceType, double price) {
        this.cateringId  = cateringId;
        this.serviceType = serviceType;
        this.price       = price;
    }

    /**
     * @return the catering service ID
     */
    public String getCateringId() { return cateringId; }

    /**
     * @return the service type
     */
    public String getServiceType() { return serviceType; }

    /**
     * @return the price of the service
     */
    public double getPrice() { return price; }
}