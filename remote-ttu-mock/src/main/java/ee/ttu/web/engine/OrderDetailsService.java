package ee.ttu.web.engine;

import ee.ttu.web.domain.OrderDetails;
import ee.ttu.web.repository.OrderDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailsService {

    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

    public OrderDetails getOrderDetails(Long orderId) {
        return orderDetailsRepository.findOne(orderId);
    }

    public List<OrderDetails> getAllOrders() {
        return orderDetailsRepository.findAll();
    }
}
