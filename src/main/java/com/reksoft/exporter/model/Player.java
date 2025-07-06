package com.reksoft.exporter.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Player {
    private Integer id;
    private String fullName;
    private String nickname;
    private Integer country;
    private String teamName;
    private String fullNameWithNickname;
}
