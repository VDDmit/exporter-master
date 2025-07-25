package com.reksoft.exporter.repository.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PlayerViewDto {
    Integer id;
    String combinedName;
    String nickName;
    Integer country;
    String teamName;
    String fullNameWithNickname;
}
