package com.reksoft.exporter.service.impl;

import com.reksoft.exporter.model.Team;
import com.reksoft.exporter.repository.TeamRepository;
import com.reksoft.exporter.repository.mapper.TeamMapper;
import com.reksoft.exporter.service.TeamService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TeamServiceImpl implements TeamService {

    TeamRepository teamRepository;
    TeamMapper teamMapper;

    @Override
    public List<Team> getAllTeams() {
        return teamRepository.getTeams()
                .stream()
                .map(teamMapper::toDomain)
                .toList();
    }
}