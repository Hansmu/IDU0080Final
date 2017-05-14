package ee.ttu.web.main.engine;

import ee.ttu.web.main.domain.json.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Endpoint
public class SoapService {

    private static final int MIN_DAYS_TO_TAKE = 1;
    private static final int MAX_DAYS_TO_TAKE = 7;

    public static final String DELIVERY_URI = "http://ttu.ee/web/delivery-info";

    @Autowired
    private RemoteService remoteService;

    @PayloadRoot(namespace = DELIVERY_URI, localPart = "getDeliveryInfo")
    @ResponsePayload
    public GetDeliveryInfoResponse getDeliveryInfo(@RequestPayload GetDeliveryInfo getDeliveryInfo) {
        OrderInfoJson orderInfoJson = remoteService.getOrderInfoById(getDeliveryInfo.getOrderId());
        CourierJson courierJson = remoteService.getCourierById(getDeliveryInfo.getCourierId());

        double courierCost = getCourierCost(orderInfoJson, courierJson);
        double discountedPrice = applyDiscountsToPriceIfApplicable(courierCost, orderInfoJson, courierJson);
        int deliveryDays = ThreadLocalRandom.current().nextInt(MIN_DAYS_TO_TAKE, MAX_DAYS_TO_TAKE + 1);
        UUID deliveryIdentifier = UUID.randomUUID();

        GetDeliveryInfoResponse getDeliveryInfoResponse = new GetDeliveryInfoResponse();
        getDeliveryInfoResponse.setDeliveryPrice(new BigDecimal(discountedPrice));
        getDeliveryInfoResponse.setDeliveryDays(deliveryDays);
        getDeliveryInfoResponse.setDeliveryIdentifier(deliveryIdentifier.toString());

        return getDeliveryInfoResponse;
    }

    private double getCourierCost(OrderInfoJson orderInfoJson, CourierJson courierJson) {
        return orderInfoJson.getOrderCost().doubleValue() * ((double)courierJson.getPercentFromOrder() / 100);
    }

    private boolean isDeliveryAddressInSameCountyWithCourier(OrderInfoJson orderInfoJson, CourierJson courierJson) {
        return courierJson.getAddresses().stream()
                .filter(address -> address.getAddress().contains(orderInfoJson.getDeliveryAddress()))
                .collect(Collectors.toList())
                .size() > 0;
    }

    private boolean isSellerAddressInSameCountryWithCourier(OrderInfoJson orderInfoJson, CourierJson courierJson) {
        return orderInfoJson.getAddresses().stream()
                .filter(sellerAddress -> isAddressWithinAddressList(sellerAddress, courierJson.getAddresses()))
                .collect(Collectors.toList())
                .size() > 0;
    }

    private boolean isAddressWithinAddressList(Address address, List<Address> addressList) {
        return addressList.stream()
                .filter(addressOfList -> addressOfList.getAddress().contains(address.getAddress()))
                .collect(Collectors.toList())
                .size() > 0;
    }

    private double applyDiscountsToPriceIfApplicable(double courierCost, OrderInfoJson orderInfoJson, CourierJson courierJson) {
        double discountedCourierCost = courierCost;

        if (isDeliveryAddressInSameCountyWithCourier(orderInfoJson, courierJson)) {
            discountedCourierCost = applyDiscountOfThirtyPercent(courierCost);
        }

        if (isSellerAddressInSameCountryWithCourier(orderInfoJson, courierJson)) {
            return applyDiscountOfThirtyPercent(discountedCourierCost);
        }

        return discountedCourierCost;
    }

    private double applyDiscountOfThirtyPercent(double courierCost) {
        return courierCost * 0.7;
    }
}
