package ee.ttu.web.main.api;

import ee.ttu.web.common.Result;
import ee.ttu.web.main.engine.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("order")
public class MainController {

    @Autowired
    private MainService mainService;

    @RequestMapping(name = "{orderId}", method = RequestMethod.GET)
    public Result processOrder(@PathVariable Long orderId) {
        return Result.ok(mainService.processOrder(orderId));
    }
}
