package com.bettorleague.microservice.model.football;

import com.bettorleague.microservice.model.json.JsonObject;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Competition extends JsonObject {
    private Long id;
    private String name;
    private CompetitionType type;
    private String code;
    private String logo;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private Date lastUpdated;
    private Area area;
    private Season currentSeason;
    private List<Season> seasons = new ArrayList<>();
    private Set<Team> teams = new HashSet<>();
    private List<Standing> standings = new ArrayList<>();
    private List<Match> matches = new ArrayList<>();
    private Set<StandingStage> availableStage = new HashSet<>();
    private Set<StandingGroup> availableGroup = new HashSet<>();

}
