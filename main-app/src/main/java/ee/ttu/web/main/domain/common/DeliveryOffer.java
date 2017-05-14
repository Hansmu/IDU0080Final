package ee.ttu.web.main.domain.common;

import java.math.BigDecimal;

public class DeliveryOffer {
    private BigDecimal deliveryPrice;
    private int deliveryDays;
    private String deliveryIdentifier;
    private long courierId;

    public DeliveryOffer(BigDecimal deliveryPrice, int deliveryDays, String deliveryIdentifier, long courierId) {
        this.deliveryPrice = deliveryPrice;
        this.deliveryDays = deliveryDays;
        this.deliveryIdentifier = deliveryIdentifier;
        this.courierId = courierId;
    }

    public BigDecimal getDeliveryPrice() {
        return deliveryPrice;
    }

    public int getDeliveryDays() {
        return deliveryDays;
    }

    public String getDeliveryIdentifier() {
        return deliveryIdentifier;
    }

    public long getCourierId() {
        return courierId;
    }
}
