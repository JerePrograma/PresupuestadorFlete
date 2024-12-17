package ar.com.envios.infrastructure.adapter.out.external;

import ar.com.envios.infrastructure.adapter.out.external.dto.MapResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DistanceServiceAdapter {

    private final RestTemplate restTemplate;

    public DistanceServiceAdapter(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public MapResponse getDistance(String origin, String destination) {
        String apiKey = "YOUR_API_KEY"; // Considera usar una configuración más segura
        String url = String.format(
                "https://maps.googleapis.com/maps/api/distancematrix/json?origins=%s&destinations=%s&key=%s",
                origin, destination, apiKey
        );

        return restTemplate.getForObject(url, MapResponse.class);
    }
}