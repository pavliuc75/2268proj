package org.camunda.bpm.getstarted.loanapproval;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.getstarted.loanapproval.misc.CustomerArrived;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/restaurant")
public class RESTController {
    private final RuntimeService runtimeService;

    public RESTController(RuntimeService runtimeService) {
        this.runtimeService = runtimeService;
    }

    @PostMapping("/customer-arrived")
    public String customerArrived(@RequestBody CustomerArrived customerArrived) {
        System.out.println(LocalDateTime.now() + "lol");
//        String sellFoodProcessDefinitionKey = "loanApproval";
//        runtimeService.startProcessInstanceByKey(sellFoodProcessDefinitionKey);
        return LocalDateTime.now() + "arrived";
    }

    @PostMapping("/customer-left")
    public String customerLeft() {
        String url = "http://localhost:8081/customerLeft";

        // Create RestTemplate with timeout configuration
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(1000); // Timeout in milliseconds (5 seconds)
        requestFactory.setReadTimeout(1000);    // Timeout in milliseconds (5 seconds)
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
