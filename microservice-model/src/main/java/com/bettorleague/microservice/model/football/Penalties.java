package com.bettorleague.microservice.model.football;


import lombok.*;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Penalties {
    private Long homeTeam;
    private Long awayTeam;
}
