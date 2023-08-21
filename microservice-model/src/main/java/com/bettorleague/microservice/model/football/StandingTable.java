package com.bettorleague.microservice.model.football;


import com.bettorleague.microservice.model.json.JsonObject;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StandingTable extends JsonObject implements Comparable<StandingTable> {
    private Long id;
    private Team team;
    private int position;
    private int playedGames;
    private int won;
    private int draw;
    private int lost;
    private int points;
    private int goalsFor;
    private int goalsAgainst;
    private int goalDifference;
    private Standing standing;


    @Override
    public int compareTo(StandingTable other) {
        return Integer.compare(this.position, other.position);
    }
}
