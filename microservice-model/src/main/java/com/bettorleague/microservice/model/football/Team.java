package com.bettorleague.microservice.model.football;

import com.bettorleague.microservice.model.json.JsonObject;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Team extends JsonObject {
    private Long id;
    private Area area;
    private String name;
    private String shortName;
    private String tla;
    private String address;
    private String phone;
    private String website;
    private String email;
    private String logo;
    private Long founded;
    private String clubColors;
    private String stadium;
    private Set<Player> squad;
}
