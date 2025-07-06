package com.reksoft.exporter.repository.adapter.api;

import com.reksoft.exporter.properties.ApiProperties;
import com.reksoft.exporter.repository.TeamRepository;
import com.reksoft.exporter.repository.dto.TeamViewDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

import static org.springframework.http.HttpMethod.GET;

@Slf4j
@Repository
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TeamApiRepository implements TeamRepository {

    RestTemplate restTemplate;
    ApiProperties apiProperties;

    static ParameterizedTypeReference<List<TeamViewDto>> TEAM_LIST_TYPE =
            new ParameterizedTypeReference<>() {
            };

    @Override
    public List<TeamViewDto> getTeams() {
        String url = apiProperties.getBaseUrl() + "/api/Teams";
        try {
            log.debug("Fetching teams from: {}", url);
            ResponseEntity<List<TeamViewDto>> response = restTemplate.exchange(
                    url,
                    GET,
                    null,
                    TEAM_LIST_TYPE
            );
            return response.getBody() != null ? response.getBody() : Collections.emptyList();
        } catch (RestClientException e) {
            log.error("Failed to fetch teams from {}", url, e);
            return Collections.emptyList(); // или выбросить исключение, если нужно "fail fast"
        }
    }
}