package ee.ttu.web.main.domain.common;

import java.time.LocalDate;

public class OfferQuality {
    private double quality;
    private String orderId;
    private Long courierId;
    private LocalDate deliveryDate;
    private double deliveryPrice;

    public OfferQuality(double quality, String orderId, Long courierId, LocalDate deliveryDate, double deliveryPrice) {
        this.quality = quality;
        this.orderId = orderId;
        this.courierId = courierId;
        this.deliveryDate = deliveryDate;
        this.deliveryPrice = deliveryPrice;
    }

    public double getQuality() {
        return quality;
    }

    public void setQuality(double quality) {
        this.quality = quality;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Long getCourierId() {
        return courierId;
    }

    public void setCourierId(Long courierId) {
        this.courierId = courierId;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public double getDeliveryPrice() {
        return deliveryPrice;
    }

    public void setDeliveryPrice(double deliveryPrice) {
        this.deliveryPrice = deliveryPrice;
    }
}
