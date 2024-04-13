package com.chomik.chomikdelivery.domain;


import com.chomik.delivery.client.dto.ShipmentStatus;
import com.fakecdek.delivery.mock.model.dto.DeliveryStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "shipment")
public class Shipment {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;
    @Column(name = "order_id")
    private String orderId;
    @Column(name = "address_from")
    private String userAddressFrom;
    @Column(name = "address_to")
    private String userAddressTo;
    @Column(name = "track_link")
    private String trackLink;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private DeliveryStatus status;

    public Shipment() {}

    public Shipment(String orderId, String userAddressFrom, String userAddressTo, DeliveryStatus status) {
        this.orderId = orderId;
        this.userAddressFrom = userAddressFrom;
        this.userAddressTo = userAddressTo;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getUserAddressFrom() {
        return userAddressFrom;
    }

    public void setUserAddressFrom(String addressFrom) {
        this.userAddressFrom = addressFrom;
    }

    public String getUserAddressTo() {
        return userAddressTo;
    }

    public void setUserAddressTo(String addressTo) {
        this.userAddressTo = addressTo;
    }

    public String getTrackLink() {
        return trackLink;
    }

    public void setTrackLink(String trackLink) {
        this.trackLink = trackLink;
    }

    public DeliveryStatus getStatus() {
        return status;
    }

    public void setStatus(DeliveryStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Shipment{" +
                "id='" + id + '\'' +
                ", orderId='" + orderId + '\'' +
                ", userAddressFrom='" + userAddressFrom + '\'' +
                ", userAddressTo='" + userAddressTo + '\'' +
                ", trackLink='" + trackLink + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}

