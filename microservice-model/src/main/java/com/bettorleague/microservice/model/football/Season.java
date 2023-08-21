package com.bettorleague.microservice.model.football;

import com.bettorleague.microservice.model.json.JsonObject;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Season extends JsonObject {
    private Long id;
    private Long currentMatchDay;
    private Long availableMatchDay;
    private Long availableMatchPerDay;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Date startDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Date endDate;
}
