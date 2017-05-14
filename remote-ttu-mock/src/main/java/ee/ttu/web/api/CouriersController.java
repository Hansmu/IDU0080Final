package ee.ttu.web.api;

import ee.ttu.web.common.Result;
import ee.ttu.web.engine.CouriersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("couriers")
public class CouriersController {

    @Autowired
    private CouriersService couriersService;

    @RequestMapping(name = "all", method = RequestMethod.GET)
    public Result getAllCouriers() {
        return Result.ok(couriersService.findAll());
    }
}
