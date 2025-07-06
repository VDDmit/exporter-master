package com.reksoft.exporter.service.impl;

import com.reksoft.exporter.model.Player;
import com.reksoft.exporter.repository.adapter.api.PlayerApiRepository;
import com.reksoft.exporter.repository.dto.PlayerViewDto;
import com.reksoft.exporter.repository.mapper.PlayerMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class PlayerServiceImplTest {

    private PlayerApiRepository playerApiRepository;
    private PlayerMapper playerMapper;
    private PlayerServiceImpl playerService;

    @BeforeEach
    void setUp() {
        playerApiRepository = mock(PlayerApiRepository.class);
        playerMapper = mock(PlayerMapper.class);
        playerService = new PlayerServiceImpl(playerApiRepository, playerMapper);
    }

    @Test
    void getPlayers_shouldReturnMappedPlayers() {
        // given
        PlayerViewDto dto = PlayerViewDto.builder()
                .id(1)
                .combinedName("John Doe")
                .nickName("JD")
                .teamName("Alpha")
                .country(4)
                .build();

        Player player = Player.builder()
                .id(1)
                .fullName("John Doe")
                .nickname("JD")
                .teamName("Alpha")
                .country(2)
                .fullNameWithNickname("John \"JD\" Doe")
                .build();

        when(playerApiRepository.getPlayers()).thenReturn(List.of(dto));
        when(playerMapper.toDomain(dto)).thenReturn(player);

        // when
        List<Player> result = playerService.getPlayers();

        // then
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getNickname()).isEqualTo("JD");
        verify(playerApiRepository).getPlayers();
        verify(playerMapper).toDomain(dto);
    }

    @Test
    void getPlayers_shouldReturnEmptyListWhenNoData() {
        when(playerApiRepository.getPlayers()).thenReturn(List.of());
        List<Player> result = playerService.getPlayers();
        assertThat(result).isEmpty();
        verify(playerApiRepository).getPlayers();
        verifyNoInteractions(playerMapper);
    }
}