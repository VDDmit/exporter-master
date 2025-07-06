package com.reksoft.exporter.service.impl;

import com.reksoft.exporter.model.Team;
import com.reksoft.exporter.repository.TeamRepository;
import com.reksoft.exporter.repository.dto.TeamViewDto;
import com.reksoft.exporter.repository.mapper.TeamMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class TeamServiceImplTest {

    private TeamRepository teamRepository;
    private TeamMapper teamMapper;
    private TeamServiceImpl teamService;

    @BeforeEach
    void setUp() {
        teamRepository = mock(TeamRepository.class);
        teamMapper = mock(TeamMapper.class);
        teamService = new TeamServiceImpl(teamRepository, teamMapper);
    }

    @Test
    void getAllTeams_shouldReturnMappedTeams() {
        // given
        TeamViewDto dto = TeamViewDto.builder()
                .id(1)
                .name("Team Alpha")
                .players(List.of())
                .build();

        Team domain = Team.builder()
                .id(1)
                .name("Team Alpha")
                .players("")
                .build();

        when(teamRepository.getTeams()).thenReturn(List.of(dto));
        when(teamMapper.toDomain(dto)).thenReturn(domain);

        // when
        List<Team> result = teamService.getAllTeams();

        // then
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getName()).isEqualTo("Team Alpha");
        verify(teamRepository).getTeams();
        verify(teamMapper).toDomain(dto);
    }

    @Test
    void getAllTeams_shouldReturnEmptyListWhenNoTeams() {
        when(teamRepository.getTeams()).thenReturn(List.of());
        List<Team> result = teamService.getAllTeams();
        assertThat(result).isEmpty();
        verify(teamRepository).getTeams();
        verifyNoInteractions(teamMapper);
    }
}