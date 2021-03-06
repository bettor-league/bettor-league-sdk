package com.bettorleague.microservice.model.football;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Area {
    @Id
    private String id;
    private String name;


    @JsonIgnore
    private Map<Source,String> ids;
}
