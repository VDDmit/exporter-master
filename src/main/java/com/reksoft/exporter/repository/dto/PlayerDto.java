package com.reksoft.exporter.repository.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PlayerDto {
    Integer id;
    String name;
    String surname;
    String nickname;
    Integer country;
    TeamDto team;
}
