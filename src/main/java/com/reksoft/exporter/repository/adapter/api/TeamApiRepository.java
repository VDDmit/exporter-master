package com.reksoft.exporter.repository.adapter.api;

import com.reksoft.exporter.properties.ApiProperties;
import com.reksoft.exporter.repository.TeamRepository;
import com.reksoft.exporter.repository.dto.TeamViewDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.springframework.http.HttpMethod.GET;

@Repository
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TeamApiRepository implements TeamRepository {
    RestTemplate restTemplate;
    ApiProperties apiProperties;

    @Override
    public List<TeamViewDto> getTeams() {
        ResponseEntity<List<TeamViewDto>> response = restTemplate.exchange(
                apiProperties.getBaseUrl() + "/api/Teams",
                GET,
                null,
                new ParameterizedTypeReference<>() {
                }
        );
        return response.getBody();
    }
}
