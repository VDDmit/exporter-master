package com.reksoft.exporter.repository.mapper;

import com.reksoft.exporter.model.Player;
import com.reksoft.exporter.repository.dto.PlayerViewDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.stream.Stream;

@Mapper(componentModel = "spring")
public interface PlayerMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "country", source = "country")
    @Mapping(target = "teamName", source = "teamName")
    @Mapping(target = "fullName", source = "combinedName")
    @Mapping(target = "nickname", source = "nickName")
    @Mapping(target = "fullNameWithNickname", source = ".", qualifiedByName = "fullNameWithNickName")
    Player toDomain(PlayerViewDto dto);

    @Named("fullNameWithNickName")
    default String getFullNameWithNickName(PlayerViewDto dto) {
        String combinedName = dto.getCombinedName();
        String nickName = dto.getNickName();

        if (isBlank(combinedName) && isBlank(nickName)) return "";

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

    default boolean isBlank(String str) {
        return str == null || str.isBlank();
    }
}