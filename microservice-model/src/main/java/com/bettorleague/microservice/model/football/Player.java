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
public class Player extends JsonObject {
    private Long id;
    private String name;
    private String firstName;
    private String lastName;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Date dateOfBirth;
    private String countryOfBirth;
    private String nationality;
    private String position;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Date lastUpdated;
    private RoleType role;
}