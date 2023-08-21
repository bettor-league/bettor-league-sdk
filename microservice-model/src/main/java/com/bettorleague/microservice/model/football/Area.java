package com.bettorleague.microservice.model.football;

import com.bettorleague.microservice.model.json.JsonObject;
import lombok.*;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Area extends JsonObject {
    private Long id;
    private String name;
}
