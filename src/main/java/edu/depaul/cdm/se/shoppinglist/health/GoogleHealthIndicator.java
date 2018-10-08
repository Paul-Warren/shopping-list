package edu.depaul.cdm.se.shoppinglist.health;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
@Endpoint(id = "google")
public class GoogleHealthIndicator implements HealthIndicator {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    @ReadOperation
    public Health health() {
        Health.Builder builder = new Health.Builder();
        try {
            ResponseEntity<String> googleStatus = restTemplate.getForEntity("https://www.google.com", String.class);
            if (googleStatus.getStatusCode().is2xxSuccessful()) {
                return builder.up().withDetail("i_feel", "happy").build();
            } else {
                return builder.down().withDetail("i_feel", "sad").build();
            }
        } catch (RestClientException rce) {
            return builder.unknown().withDetail("error", rce.getMessage()).build();
        }
    }
}
