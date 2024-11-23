package org.camunda.bpm.getstarted.loanapproval;

import org.camunda.bpm.getstarted.loanapproval.misc.Customer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {
    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @PostMapping("/customer-arrived")
    public String customerArrived(@RequestBody Customer customer) {
        return restaurantService.startNewProcessInstance(customer);
    }
}
