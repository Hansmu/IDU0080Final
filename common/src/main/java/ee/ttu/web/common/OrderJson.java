package ee.ttu.web.common;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

@SuppressWarnings("Since15")
public class OrderJson {
    private Long courierId;
    private Long orderId;
    private String orderIdentifier;
    private String deliveryDate;
    private double orderPrice;

    public OrderJson() {
    }

    public OrderJson(Long courierId, Long orderId, String orderIdentifier, String deliveryDate, double orderPrice) {
        this.courierId = courierId;
        this.orderId = orderId;
        this.orderIdentifier = orderIdentifier;
        this.deliveryDate = deliveryDate;
        this.orderPrice = orderPrice;
    }

    public Long getCourierId() {
        return courierId;
    }

    public void setCourierId(Long courierId) {
        this.courierId = courierId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderIdentifier() {
        return orderIdentifier;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderIdentifier(String orderIdentifier) {
        this.orderIdentifier = orderIdentifier;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public void setOrderPrice(double orderPrice) {
        this.orderPrice = orderPrice;
    }
}
