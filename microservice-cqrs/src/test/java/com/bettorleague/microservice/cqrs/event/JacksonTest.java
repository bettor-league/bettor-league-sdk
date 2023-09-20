package com.bettorleague.microservice.cqrs.event;

import com.bettorleague.microservice.common.jackson.ObjectMapperConfig;
import com.bettorleague.microservice.cqrs.domain.Event;
import com.bettorleague.microservice.cqrs.event.domain.EntityEvent.EntityCreated;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(MockitoJUnitRunner.class)
public class JacksonTest {
    private ObjectMapper objectMapper;
    @Before
    public void before(){
        final ObjectMapperConfig config = new ObjectMapperConfig();
        objectMapper = config.objectMapper();
    }

    @Test
    public void test() {
        final EntityCreated entityCreated = new EntityCreated("aggregateIdentifier", "field");
        final Event event = new Event(entityCreated);
        assertThat(objectMapper).isNotNull();

        JsonNode messageAsJson = objectMapper.convertValue(event, JsonNode.class);
        assertThat(messageAsJson).isNotNull();
        assertThat(messageAsJson.has("aggregateIdentifier")).isTrue();
    }
}
