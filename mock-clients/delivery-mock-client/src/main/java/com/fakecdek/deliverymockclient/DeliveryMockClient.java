package com.fakecdek.deliverymockclient;


import com.fakecdek.deliverymockclient.dto.ApplyForDeliveryRequest;
import com.fakecdek.deliverymockclient.dto.ApplyForDeliveryResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public class DeliveryMockClient {
    private String baseUrl;
    private RestTemplate restTemplate;

    public DeliveryMockClient(String baseUrl, RestTemplate restTemplate) {
        this.baseUrl = baseUrl;
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<?> applyForDelivery(ApplyForDeliveryRequest request) {
        UriComponentsBuilder uri = createBuilder("/api/fakecdek/apply");
        return restTemplate.postForEntity(uri.toUriString(), request, ApplyForDeliveryResponse.class);
    }

    private UriComponentsBuilder createBuilder(String method) {
        return UriComponentsBuilder.fromHttpUrl(baseUrl).path(method);
    }
}
