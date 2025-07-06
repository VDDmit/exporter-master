package com.reksoft.exporter.repository.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RatingDto {
    Integer id;
    Integer score;
    LocalDateTime atMoment;
    Integer teamId;
    TeamDto team;
}