package ee.ttu.web.main.domain.common;

public class OfferQuality {
    private double quality;
    private String orderId;
    private Long courierId;

    public OfferQuality(double quality, String orderId, Long courierId) {
        this.quality = quality;
        this.orderId = orderId;
        this.courierId = courierId;
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
}
