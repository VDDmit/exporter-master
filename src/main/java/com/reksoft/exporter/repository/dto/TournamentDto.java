package com.reksoft.exporter.repository.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TournamentDto {
    Integer id;
    String name;
    String organizer;
    LocalDateTime startDate;
    LocalDateTime endDate;
    List<TournamentParticipantInfoDto> teamParticipantInfos;
    List<TournamentPrizeDto> tournamentPrizes;
}
