package com.bettorleague.microservice.model.football;

import com.bettorleague.microservice.model.json.JsonObject;
import lombok.*;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Standing extends JsonObject implements Comparable<Standing> {
    private Long id;
    private StandingStage stage;
    private StandingType type;
    private StandingGroup group;
    private List<StandingTable> table = new ArrayList<>();
    private Competition competition;

    @Override
    public int compareTo(@NonNull Standing other) {
        if (nonNull(this.group) && nonNull(other.getGroup())) {
            int group = this.group.compareTo(other.getGroup());
            if (group != 0) {
                return group;
            }
        }
        return 1;
    }

}
