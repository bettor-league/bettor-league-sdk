package com.bettorleague.microservice.model.football;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.Map;
import java.util.Set;

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
    private Set<String> logos;
    private Season season;
    private Gender gender;

    @JsonIgnore
    private Map<Source,String> ids;
}
