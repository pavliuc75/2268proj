package org.camunda.bpm.getstarted.loanapproval.process.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.getstarted.loanapproval.RestaurantService;
import org.springframework.stereotype.Component;

@Component("sendCustomerLeftEventService")
public class SendCustomerLeftEventServiceDelegate implements JavaDelegate {
    private RestaurantService restaurantService;

    public SendCustomerLeftEventServiceDelegate(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        restaurantService.sendCustomerLeftEvent();

        String pid = delegateExecution.getProcessInstanceId();
        System.out.println("SendCustomerLeftEventServiceDelegate executed" + pid);
    }
}
