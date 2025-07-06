package com.reksoft.exporter.repository.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TeamViewDto {
    Integer id;
    String name;
    List<PlayerViewDto> players;
}
