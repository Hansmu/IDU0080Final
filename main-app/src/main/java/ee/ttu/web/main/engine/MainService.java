package ee.ttu.web.main.engine;

import ee.ttu.web.common.Result;
import ee.ttu.web.main.domain.json.Courier;
import ee.ttu.web.main.domain.json.OrderDetails;
import ee.ttu.web.main.soap.GetDeliveryInfo;
import ee.ttu.web.main.soap.GetDeliveryInfoResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import java.util.List;

@Service
public class MainService extends WebServiceGatewaySupport {

    private static final String REMOTE_TTU_MOCK = "http://localhost:9100";

    public Long processOrder(Long orderId) {
        OrderDetails orderDetails = getOrderDetails(orderId);
        List<Courier> couriers = getCouriers();
        GetDeliveryInfoResponse deliverOffer = getDeliveryOffer();
        return 0L;
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

    private GetDeliveryInfoResponse getDeliveryOffer() {
        GetDeliveryInfo deliveryOfferRequest = new GetDeliveryInfo();

        GetDeliveryInfoResponse response = (GetDeliveryInfoResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:9300/ws",
                        deliveryOfferRequest,
                        new SoapActionCallback("http://ttu.ee/web/delivery-info"));

        return response;
    }
}
