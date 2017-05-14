package ee.ttu.web.main.api;

import ee.ttu.web.common.Result;
import ee.ttu.web.main.engine.CouriersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("couriers")
public class CouriersController {

    @Autowired
    private CouriersService couriersService;

    @RequestMapping(value = "all", method = RequestMethod.GET)
    public Result getCouriers() {
        return Result.ok(couriersService.getCouriers());
    }
}
