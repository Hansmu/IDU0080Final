package ee.ttu.web.main.engine;

import ee.ttu.web.common.Result;
import ee.ttu.web.main.domain.json.Courier;
import ee.ttu.web.main.domain.json.OrderDetails;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CouriersService {

    private static final String REMOTE_TTU_MOCK = "http://localhost:9100";

    public List<Courier> getCouriers() {
        RestTemplate restTemplate = new RestTemplate();
        Result<List<Courier>> couriersResult = restTemplate.exchange(REMOTE_TTU_MOCK + "/couriers/all", HttpMethod.GET,
                null, new ParameterizedTypeReference<Result<List<Courier>>>() {}).getBody();
        return couriersResult.getData();
    }
}
