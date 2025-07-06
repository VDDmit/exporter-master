package com.reksoft.exporter.repository.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TeamDto {
    Integer id;
    String name;
    List<PlayerDto> players;
    List<TournamentParticipantInfoDto> teamTournamentResults;
    List<RatingDto> teamRatings;
    List<MatchHistoryDto> matchesWon;
    List<MatchHistoryDto> matchesLost;
}
