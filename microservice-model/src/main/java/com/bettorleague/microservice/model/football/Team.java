package com.bettorleague.microservice.model.football;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.Email;
import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Team {
    @Id
    private String id;
    private Area area;
    private String name;
    private String shortName;
    private String acronym;
    private String address;
    private String phone;
    private String website;
    private String logo;
    @Email
    private String email;
    private int founded;
    private List<String> colors;
    private String stadium;

    @JsonIgnore
    private Map<Source,String> ids;
}
