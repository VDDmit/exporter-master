package com.reksoft.exporter.repository.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TeamViewDto {
    Integer id;
    String name;
    List<PlayerViewDto> players;
}
