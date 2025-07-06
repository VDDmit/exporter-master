package com.reksoft.exporter.service.impl;

import com.reksoft.exporter.model.Player;
import com.reksoft.exporter.repository.adapter.api.PlayerApiRepository;
import com.reksoft.exporter.repository.mapper.PlayerMapper;
import com.reksoft.exporter.service.PlayerService;
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
public class PlayerServiceImpl implements PlayerService {

    PlayerApiRepository playerApiRepository;
    PlayerMapper playerMapper;

    @Override
    public List<Player> getPlayers() {
        return playerApiRepository.getPlayers()
                .stream()
                .map(playerMapper::toDomain)
                .toList();
    }
}