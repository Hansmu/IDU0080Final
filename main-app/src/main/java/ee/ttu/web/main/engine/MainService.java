package ee.ttu.web.main.engine;

import ee.ttu.web.common.OrderJson;
import ee.ttu.web.common.Result;
import ee.ttu.web.common.TrackingId;
import ee.ttu.web.main.domain.common.DeliveryOffer;
import ee.ttu.web.main.domain.common.OfferQuality;
import ee.ttu.web.main.domain.json.Courier;
import ee.ttu.web.main.domain.json.MadeOrderJson;
import ee.ttu.web.main.domain.json.OrderDetails;
import ee.ttu.web.main.soap.GetDeliveryInfoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MainService {

    private static final String REMOTE_TTU_MOCK = "http://localhost:9100";

    @Autowired
    private OfferClient offerClient;


    public MadeOrderJson getMadeOrderByTrackingNumber(TrackingId trackingId) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<TrackingId> order = new HttpEntity<>(trackingId);

        Result<MadeOrderJson> response = restTemplate.exchange("http://localhost:11111/order/tracking", HttpMethod.POST, order,
                new ParameterizedTypeReference<Result<MadeOrderJson>>() {}).getBody();
        return response.getData();
    }

    public String processOrderAndGetTrackingNumber(Long orderId) {
        OrderDetails orderDetails = getOrderDetails(orderId);
        List<Courier> couriers = getCouriers();

        List<DeliveryOffer> deliveryOffers = getDeliveryOffers(couriers, orderDetails);
        OfferQuality bestOffer = getBestOffer(deliveryOffers);
        double totalOrderCost = bestOffer.getDeliveryPrice() + orderDetails.getOrderCost().doubleValue();
        String deliveryDateAsString = bestOffer.getDeliveryDate().getYear() + "-" + bestOffer.getDeliveryDate().getMonthValue() + "-" + bestOffer.getDeliveryDate().getDayOfMonth();

        OrderJson orderJson = new OrderJson(bestOffer.getCourierId(), orderId, bestOffer.getOrderId(), deliveryDateAsString, totalOrderCost);
        String trackingNumber = addNewOrder(orderJson);

        return trackingNumber;
    }

    private List<DeliveryOffer> getDeliveryOffers(List<Courier> couriers, OrderDetails orderDetails) {
        return couriers.stream()
                .map(courier -> {
                    GetDeliveryInfoResponse response = offerClient.getDeliveryOffer(orderDetails.getId(), courier.getId());
                    return getDeliveryOfferFromDeliveryInfoResponse(response, courier.getId());
                })
                .collect(Collectors.toList());
    }

    private DeliveryOffer getDeliveryOfferFromDeliveryInfoResponse(GetDeliveryInfoResponse response, long courierId) {
        return new DeliveryOffer(response.getDeliveryPrice(), response.getDeliveryDays(), response.getDeliveryIdentifier(), courierId);
    }

    private OfferQuality getBestOffer(List<DeliveryOffer> deliveryOffer) {
        return deliveryOffer.stream()
                .map(this::getOfferQualityFromDeliveryOffer)
                .sorted((oq1, oq2) -> new Double(oq1.getQuality()).compareTo(oq2.getQuality()))
                .collect(Collectors.toList())
                .get(0);
    }

    private OfferQuality getOfferQualityFromDeliveryOffer(DeliveryOffer offer) {
        double offerQuality = getOfferQuality(offer);
        String trackingNumber = offer.getDeliveryIdentifier();
        long courierId = offer.getCourierId();
        LocalDate deliveryDate = LocalDate.now().plusDays(offer.getDeliveryDays());

        return new OfferQuality(offerQuality, trackingNumber, courierId, deliveryDate, offer.getDeliveryPrice().doubleValue());
    }

    private double getOfferQuality(DeliveryOffer deliveryOffer) {
        return deliveryOffer.getDeliveryPrice().longValue() * 0.01 * deliveryOffer.getDeliveryDays();
    }

    private OrderDetails getOrderDetails(Long orderId) {
        RestTemplate restTemplate = new RestTemplate();
        String orderDetailsUrl = REMOTE_TTU_MOCK + "/order/" + orderId + "/details";

        Result<OrderDetails> orderDetailsResult = restTemplate.exchange(orderDetailsUrl, HttpMethod.GET,
                null, new ParameterizedTypeReference<Result<OrderDetails>>() {}).getBody();

        return orderDetailsResult.getData();
    }

    private List<Courier> getCouriers() {
        RestTemplate restTemplate = new RestTemplate();
        Result<List<Courier>> couriersResult = restTemplate.exchange("http://localhost:9200/couriers/all", HttpMethod.GET,
                null, new ParameterizedTypeReference<Result<List<Courier>>>() {}).getBody();
        return couriersResult.getData();
    }

    private String addNewOrder(OrderJson orderJson) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<OrderJson> order = new HttpEntity<>(orderJson);

        Result<String> responseEntity = restTemplate.exchange("http://localhost:11111/order/make", HttpMethod.POST, order,
                new ParameterizedTypeReference<Result<String>>() {}).getBody();

        return responseEntity.getData();
    }

    public List<OrderDetails> getAllOrders() {
        RestTemplate restTemplate = new RestTemplate();
        String orderDetailsUrl = REMOTE_TTU_MOCK + "/order/all";

        Result<List<OrderDetails>> orderDetailsResult = restTemplate.exchange(orderDetailsUrl, HttpMethod.GET,
                null, new ParameterizedTypeReference<Result<List<OrderDetails>>>() {}).getBody();

        return orderDetailsResult.getData();
    }
}
