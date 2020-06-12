package com.bettorleague.microservice.model.football;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Competition {
    @Id
    private String id;
    private String name;
    private Area area;
    private CompetitionType type;
    private String logo;
    private Season season;
    private Gender gender;
}
