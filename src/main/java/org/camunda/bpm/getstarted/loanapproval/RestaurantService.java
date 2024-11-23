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

    public String startNewProcessInstance(Customer customer) {
        String sellFoodProcessDefinitionKey = "loanApproval";
        ProcessInstance pi = runtimeService.startProcessInstanceByKey(sellFoodProcessDefinitionKey);

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