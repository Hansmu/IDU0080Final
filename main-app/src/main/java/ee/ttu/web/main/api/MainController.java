package ee.ttu.web.main.api;

import ee.ttu.web.common.Result;
import ee.ttu.web.main.engine.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("order")
@CrossOrigin
public class MainController {

    @Autowired
    private MainService mainService;

    @RequestMapping(value = "{orderId}", method = RequestMethod.GET)
    public Result processOrder(@PathVariable Long orderId) {
        return Result.ok(mainService.processOrderAndGetTrackingNumber(orderId));
    }
}
