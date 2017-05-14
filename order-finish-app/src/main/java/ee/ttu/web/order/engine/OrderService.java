package ee.ttu.web.order.engine;

import ee.ttu.web.common.OrderJson;
import ee.ttu.web.order.domain.Order;
import ee.ttu.web.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public String makeOrder(OrderJson orderJson) {
        String trackingNumber = generateTrackingNumber(orderJson);

        Order order = new Order();

        order.setCourierId(orderJson.getCourierId());
        order.setOrderId(orderJson.getOrderId());
        order.setTrackingNumber(trackingNumber);
        order.setDeliveryDate(orderJson.getDeliveryDate());
        order.setOrderPrice(orderJson.getOrderPrice());

        orderRepository.save(order);

        return trackingNumber;
    }

    private String generateTrackingNumber(OrderJson orderJson) {
        String identifierWithoutDashes = orderJson.getOrderIdentifier().replace("-", "");
        String shortenedIdentifier = identifierWithoutDashes.substring(0, 11);
        LocalDate currentDate = LocalDate.now();
        String identifierExtension = "#" + currentDate.getYear() + currentDate.getMonthValue() + currentDate.getDayOfMonth();
        return shortenedIdentifier.toUpperCase() + identifierExtension;
    }

    public Order getOrderByTrackingId(String trackingId) {
        return orderRepository.findOneByTrackingId(trackingId);
    }
}
