package com.reksoft.exporter.service.impl;

import com.reksoft.exporter.model.Team;
import com.reksoft.exporter.repository.TeamRepository;
import com.reksoft.exporter.repository.dto.PlayerViewDto;
import com.reksoft.exporter.repository.dto.TeamViewDto;
import com.reksoft.exporter.service.TeamService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TeamServiceImpl implements TeamService {

    TeamRepository teamRepository;

    @Override
    public List<Team> getAllTeams() {
        return teamRepository.getTeams()
                .stream()
                .map(this::mapToTeam)
                .collect(Collectors.toList());
    }

    private Team mapToTeam(TeamViewDto dto) {
        String players = dto.getPlayers() == null ? "" :
                dto.getPlayers().stream()
                        .map(PlayerViewDto::getCombinedName)
                        .filter(this::isNotBlank)
                        .collect(Collectors.joining(", "));

        return Team.builder()
                .id(dto.getId())
                .name(dto.getName())
                .players(players)
                .build();
    }

    private boolean isNotBlank(String str) {
        return str != null && !str.isBlank();
    }
}