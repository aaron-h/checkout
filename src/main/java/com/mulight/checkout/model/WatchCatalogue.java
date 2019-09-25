package com.mulight.checkout.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Simple business object representing a catalogue.
 *
 * @author Aaron
 */

@Entity
@Table(name = "watch_catalogue")
public class WatchCatalogue extends BaseEntity {

    @Column(name = "watch_id")
    private String watchId;

    @Column(name = "watch_name")
    private String watchName;

    @Column(name = "unit_price")
    private Double unitPrice;

    @Column(name = "discount")
    private String discount;

    public WatchCatalogue() {
    }

    public WatchCatalogue(String watchId, String watchName, Double unitPrice, String discount) {
        this.watchId = watchId;
        this.watchName = watchName;
        this.unitPrice = unitPrice;
        this.discount = discount;
    }

    public String getWatchId() {
        return watchId;
    }

    public void setWatchId(String watchId) {
        this.watchId = watchId;
    }

    public String getWatchName() {
        return watchName;
    }

    public void setWatchName(String watchName) {
        this.watchName = watchName;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }
}
