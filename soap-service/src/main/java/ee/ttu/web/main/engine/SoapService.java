package ee.ttu.web.main.engine;

import ee.ttu.web.main.domain.json.GetDeliveryInfoResponse;
import ee.ttu.web.main.domain.json.GetDeliveryInfo;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.math.BigDecimal;

@Endpoint
public class SoapService {

    public static final String DELIVERY_URI = "http://ttu.ee/web/delivery-info";

    @PayloadRoot(namespace = DELIVERY_URI, localPart = "getDeliveryInfo")
    @ResponsePayload
    public GetDeliveryInfoResponse getDeliveryInfo(@RequestPayload GetDeliveryInfo getDeliveryInfo) {
        return new GetDeliveryInfoResponse();
    }
}
