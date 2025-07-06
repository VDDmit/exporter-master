package com.reksoft.exporter.repository.adapter.api;

import com.reksoft.exporter.properties.ApiProperties;
import com.reksoft.exporter.repository.PlayerRepository;
import com.reksoft.exporter.repository.dto.PlayerViewDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.List;

import static org.springframework.http.HttpMethod.GET;

@Slf4j
@Repository
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PlayerApiRepository implements PlayerRepository {

    RestTemplate restTemplate;
    ApiProperties apiProperties;

    private static final ParameterizedTypeReference<List<PlayerViewDto>> PLAYER_LIST_TYPE =
            new ParameterizedTypeReference<>() {
            };

    @Override
    public List<PlayerViewDto> getPlayers() {
        String url = apiProperties.getBaseUrl() + "/api/Players";
        return fetchPlayerList(url);
    }

    public List<PlayerViewDto> getFilteredPlayers(String filter) {
        String uri = UriComponentsBuilder
                .fromHttpUrl(apiProperties.getBaseUrl() + "/api/Players/filter")
                .queryParam("filter", filter)
                .toUriString();
        return fetchPlayerList(uri);
    }

    private List<PlayerViewDto> fetchPlayerList(String url) {
        try {
            log.debug("Fetching players from: {}", url);
            ResponseEntity<List<PlayerViewDto>> response = restTemplate.exchange(
                    url,
                    GET,
                    null,
                    PLAYER_LIST_TYPE
            );
            return response.getBody() != null ? response.getBody() : Collections.emptyList();
        } catch (RestClientException e) {
            log.error("Failed to fetch players from URL: {}", url, e);
            return Collections.emptyList(); // или выбросить исключение, если нужно "fail fast"
        }
    }
}