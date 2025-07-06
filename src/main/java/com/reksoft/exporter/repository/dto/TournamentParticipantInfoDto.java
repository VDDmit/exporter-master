package com.reksoft.exporter.repository.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TournamentParticipantInfoDto {
    Integer id;
    Integer standing;
    Integer place;
    Integer teamId;
    Integer tournamentId;
    TeamDto team;
    TournamentDto tournament;
}