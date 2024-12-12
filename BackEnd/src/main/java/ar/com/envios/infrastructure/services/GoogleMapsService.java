package ar.com.envios.infrastructure.services;

import ar.com.envios.domain.service.IDistanceCalculator;
import ar.com.envios.infrastructure.adapter.out.external.dto.MapResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GoogleMapsService implements IDistanceCalculator {

    private static final String API_KEY = "YOUR_GOOGLE_API_KEY";
    private static final String DISTANCE_MATRIX_URL = "https://maps.googleapis.com/maps/api/distancematrix/json";

    private final RestTemplate restTemplate;

    public GoogleMapsService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public double calculateDistance(String origin, String destination) {
        String url = DISTANCE_MATRIX_URL + "?origins=" + origin + "&destinations=" + destination + "&key=" + API_KEY;
        var response = restTemplate.getForObject(url, MapResponse.class);

        if (response.getRows() != null && !response.getRows().isEmpty()) {
            var element = response.getRows().get(0).getElements().get(0);
            if ("OK".equals(element.getStatus())) {
                return element.getDistance().getValue() / 1000.0; // Convert meters to kilometers
            }
        }
        throw new IllegalArgumentException("Could not calculate distance. Verify the origin and destination.");
    }
}
