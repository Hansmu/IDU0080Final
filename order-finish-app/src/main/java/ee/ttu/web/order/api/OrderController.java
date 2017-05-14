package ee.ttu.web.order.api;

import ee.ttu.web.common.OrderJson;
import ee.ttu.web.common.Result;
import ee.ttu.web.common.TrackingId;
import ee.ttu.web.order.engine.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("order")
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "make", method = RequestMethod.POST)
    public Result makeOrder(@RequestBody OrderJson orderJson) {
        return Result.ok(orderService.makeOrder(orderJson));
    }

    @RequestMapping(value = "{trackingId}", method = RequestMethod.POST)
    public Result getOrderByTrackingId(@RequestBody TrackingId trackingId) {
        return Result.ok(orderService.getOrderByTrackingId(trackingId.getTrackingId()));
    }
}
