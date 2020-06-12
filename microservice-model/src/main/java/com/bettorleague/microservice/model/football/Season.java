package com.bettorleague.microservice.model.football;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.Instant;
import java.util.Map;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Season {
    @Id
    private String id;
    private Instant startDate;
    private Instant endDate;
    private int matchDay;
    private Set<Stage> availableStages;

    @JsonIgnore
    private String competitionId;
    @JsonIgnore
    private Map<Source,String> ids;
}
