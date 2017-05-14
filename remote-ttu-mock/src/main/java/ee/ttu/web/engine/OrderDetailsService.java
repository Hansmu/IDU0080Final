package ee.ttu.web.engine;

import ee.ttu.web.repository.OrderDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailsService {

    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

    public <I> I getOrderDetails(String orderId) {
        return null;
    }
}
