package com.reksoft.exporter.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Player {
    Integer id;
    String fullName;
    String nickname;
    Integer country;
    String teamName;
    String fullNameWithNickname;
}
