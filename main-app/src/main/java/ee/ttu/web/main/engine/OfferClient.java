package ee.ttu.web.main.engine;

import ee.ttu.web.main.soap.GetDeliveryInfo;
import ee.ttu.web.main.soap.GetDeliveryInfoResponse;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class OfferClient extends WebServiceGatewaySupport {

    public GetDeliveryInfoResponse getDeliveryOffer(long orderId, long courierId) {
        GetDeliveryInfo deliveryOfferRequest = new GetDeliveryInfo();
        deliveryOfferRequest.setOrderId(orderId);
        deliveryOfferRequest.setCourierId(courierId);

        GetDeliveryInfoResponse response = (GetDeliveryInfoResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:9300/ws",
                        deliveryOfferRequest,
                        new SoapActionCallback("http://ttu.ee/web/delivery-info"));

        return response;
    }
}
