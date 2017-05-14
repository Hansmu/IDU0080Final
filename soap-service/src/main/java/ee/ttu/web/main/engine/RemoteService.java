package ee.ttu.web.main.engine;

import ee.ttu.web.common.Result;
import ee.ttu.web.main.domain.json.CourierJson;
import ee.ttu.web.main.domain.json.OrderInfoJson;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RemoteService {

    public OrderInfoJson getOrderInfoById(Long orderInfoId) {
        RestTemplate restTemplate = new RestTemplate();
        Result<OrderInfoJson> responseEntity = restTemplate.exchange("http://localhost:9100/order/" + orderInfoId + "/details", HttpMethod.GET,
                null, new ParameterizedTypeReference<Result<OrderInfoJson>>() {}).getBody();
        return responseEntity.getData();
    }

    public CourierJson getCourierById(long courierId) {
        RestTemplate restTemplate = new RestTemplate();
        Result<CourierJson> responseEntity = restTemplate.exchange("http://localhost:9100/couriers/" + courierId, HttpMethod.GET,
                null, new ParameterizedTypeReference<Result<CourierJson>>() {}).getBody();
        return responseEntity.getData();
    }
}
