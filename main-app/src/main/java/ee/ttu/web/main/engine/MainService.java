package ee.ttu.web.main.engine;

import ee.ttu.web.common.Result;
import ee.ttu.web.main.domain.common.DeliveryOffer;
import ee.ttu.web.main.domain.common.OfferQuality;
import ee.ttu.web.main.domain.json.Courier;
import ee.ttu.web.main.domain.json.OrderDetails;
import ee.ttu.web.main.soap.GetDeliveryInfoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MainService {

    private static final String REMOTE_TTU_MOCK = "http://localhost:9100";

    @Autowired
    private OfferClient offerClient;

    public OfferQuality getBestOfferForOrder(Long orderId) {
        OrderDetails orderDetails = getOrderDetails(orderId);
        List<Courier> couriers = getCouriers();

        List<DeliveryOffer> deliveryOffers = getDeliveryOffers(couriers, orderDetails);

        return getBestOffer(deliveryOffers);
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
                .map(offer -> new OfferQuality(getOfferQuality(offer), offer.getDeliveryIdentifier(), offer.getCourierId()))
                .sorted((oq1, oq2) -> new Double(oq1.getQuality()).compareTo(oq2.getQuality()))
                .collect(Collectors.toList())
                .get(0);
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
}
