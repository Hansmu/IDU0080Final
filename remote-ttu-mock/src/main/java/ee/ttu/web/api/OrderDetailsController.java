package ee.ttu.web.api;

import ee.ttu.web.common.Result;
import ee.ttu.web.engine.OrderDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("order")
public class OrderDetailsController {

    @Autowired
    private OrderDetailsService orderDetailsService;

    @RequestMapping(value = "{orderId}/details", method = RequestMethod.GET)
    public Result getOrderDetails(@PathVariable("orderId") Long orderId) {
        return Result.ok(orderDetailsService.getOrderDetails(orderId));
    }

    @RequestMapping(value = "all", method = RequestMethod.GET)
    public Result getAllOrders() {
        return Result.ok(orderDetailsService.getAllOrders());
    }
}
