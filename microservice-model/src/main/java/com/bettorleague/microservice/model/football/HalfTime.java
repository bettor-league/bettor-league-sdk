package com.bettorleague.microservice.model.football;


import com.bettorleague.microservice.model.json.JsonObject;
import lombok.*;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HalfTime extends JsonObject {
    private Long homeTeam;
    private Long awayTeam;
}
