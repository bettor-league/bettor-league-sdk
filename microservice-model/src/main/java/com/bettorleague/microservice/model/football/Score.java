package com.bettorleague.microservice.model.football;


import com.bettorleague.microservice.model.json.JsonObject;
import lombok.*;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Score extends JsonObject {
    private Long id;
    private ScoreResult winner;
    private ScoreDuration duration;
    private Match match;
    private FullTime fullTime;
    private HalfTime halfTime;
    private ExtraTime extraTime;
    private Penalties penalties;
}
