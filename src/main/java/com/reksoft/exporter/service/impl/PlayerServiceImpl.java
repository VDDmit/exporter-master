package com.reksoft.exporter.service.impl;

import com.reksoft.exporter.model.Player;
import com.reksoft.exporter.repository.adapter.api.PlayerApiRepository;
import com.reksoft.exporter.repository.dto.PlayerViewDto;
import com.reksoft.exporter.service.PlayerService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PlayerServiceImpl implements PlayerService {

    PlayerApiRepository playerApiRepository;

    @Override
    public List<Player> getPlayers() {
        return playerApiRepository.getPlayers()
                .stream()
                .map(this::mapToPlayer)
                .collect(Collectors.toList());
    }

    private Player mapToPlayer(PlayerViewDto dto) {
        return Player.builder()
                .id(dto.getId())
                .country(dto.getCountry())
                .nickname(dto.getNickName())
                .fullName(dto.getCombinedName())
                .teamName(dto.getTeamName())
                .fullNameWithNickname(getFullNameWithNickName(dto.getCombinedName(), dto.getNickName()))
                .build();
    }

    private String getFullNameWithNickName(String combinedName, String nickName) {
        if (isBlank(combinedName) && isBlank(nickName)) {
            return "";
        }

        String firstName = "";
        String lastName = "";

        if (!isBlank(combinedName)) {
            String[] parts = combinedName.trim().split("\\s+", 2);
            firstName = parts[0];
            if (parts.length > 1) {
                lastName = parts[1];
            }
        }

        String nick = !isBlank(nickName) ? "\"" + nickName.trim() + "\"" : "";

        return String.join(" ", Stream.of(firstName, nick, lastName)
                .filter(s -> !s.isBlank())
                .toList());
    }

    private boolean isBlank(String str) {
        return str == null || str.isBlank();
    }
}