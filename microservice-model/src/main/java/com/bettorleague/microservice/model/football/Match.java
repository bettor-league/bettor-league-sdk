package com.bettorleague.microservice.model.football;

import com.bettorleague.microservice.model.json.JsonObject;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.lang.NonNull;

import java.util.Date;

import static java.util.Objects.nonNull;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Match extends JsonObject implements Comparable<Match> {
    private Long id;
    private Season season;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private Date utcDate;
    private String status;
    private Integer matchDay;
    private StandingStage stage;
    private StandingGroup group;
    private Team homeTeam;
    private Team awayTeam;
    private Score score;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Date lastUpdated;
    private Competition competition;

    @Override
    public int compareTo(@NonNull Match other) {
        if (nonNull(matchDay) && nonNull(other.getMatchDay())) {
            int matchDay = this.matchDay.compareTo(other.getMatchDay());
            if (matchDay != 0) {
                return matchDay;
            }
        }
        int homeTeam = this.getHomeTeam().getName().compareTo(other.getHomeTeam().getName());
        if (homeTeam != 0) {
            return homeTeam;
        }
        return this.getAwayTeam().getName().compareTo(other.getAwayTeam().getName());
    }

}
