package org.camunda.bpm.getstarted.loanapproval;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.getstarted.loanapproval.misc.Customer;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class RestaurantService {
    private final RuntimeService runtimeService;

    public RestaurantService(RuntimeService runtimeService) {
        this.runtimeService = runtimeService;
    }

    public String startNewProcessInstanceFromCustomerArrival(Customer customer) {
        String sellFoodProcessDefinitionKey = "loanApproval";
        String messageName = "CustomerArrived";

        Map<String, Object> variables = new HashMap<>();
        variables.put("age", customer.age);
        variables.put("isPhysicalCustomer", true);

        ProcessInstance pi = runtimeService.createMessageCorrelation(messageName)
                .setVariables(variables)
                .correlateStartMessage();

//        ProcessInstance pi = runtimeService.startProcessInstanceByKey(sellFoodProcessDefinitionKey, variables);

        String s = "Started process instance: " + pi.getId();
        System.out.println(s);
        return s;
    }

    public String startNewProcessInstanceFromOrderReceived() {
        String messageName = "OrderReceived";

        Map<String, Object> variables = new HashMap<>();
        variables.put("isPhysicalCustomer", false);


        ProcessInstance pi = runtimeService.createMessageCorrelation(messageName)
                .setVariables(variables)
                .correlateStartMessage();

        String s = "Started process instance: " + pi.getId();
        System.out.println(s);
        return s;
    }

    public String sendCustomerLeftEvent() {
        String url = "http://localhost:8081/customerLeft";

        // Create RestTemplate with timeout configuration
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(1000); // Timeout in milliseconds (1 seconds)
        requestFactory.setReadTimeout(1000);    // Timeout in milliseconds (1 seconds)
        RestTemplate restTemplate = new RestTemplate(requestFactory);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("type", "customerLeft");

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);

        try {
            restTemplate.postForObject(url, entity, String.class);
        } catch (Exception e) {
            System.err.println("Error occurred: " + e.getMessage());
        }

        System.out.println(LocalDateTime.now() + " left");
        return LocalDateTime.now() + " left";
    }
}
