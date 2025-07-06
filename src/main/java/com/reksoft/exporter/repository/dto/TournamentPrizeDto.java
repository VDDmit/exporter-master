package com.reksoft.exporter.repository.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TournamentPrizeDto {
    Integer id;
    Integer place;
    Integer prize;
    Integer tournamentId;
    TournamentDto tournament;
}
