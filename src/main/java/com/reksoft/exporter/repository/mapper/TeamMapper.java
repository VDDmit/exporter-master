package com.reksoft.exporter.repository.mapper;

import com.reksoft.exporter.model.Team;
import com.reksoft.exporter.repository.dto.PlayerViewDto;
import com.reksoft.exporter.repository.dto.TeamViewDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface TeamMapper {

    @Mapping(target = "players", source = ".", qualifiedByName = "formatPlayersList")
    Team toDomain(TeamViewDto dto);

    @Named("formatPlayersList")
    default String formatPlayers(TeamViewDto dto) {
        if (dto.getPlayers() == null || dto.getPlayers().isEmpty()) {
            return "";
        }

        return dto.getPlayers().stream()
                .map(PlayerViewDto::getCombinedName)
                .filter(name -> name != null && !name.isBlank())
                .collect(Collectors.joining(", "));
    }
}