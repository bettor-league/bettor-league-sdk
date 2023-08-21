package com.bettorleague.microservice.model.json;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashMap;
import java.util.Map;

public class JsonObject {

    @JsonIgnore
    private final Map<String, Object> additionalProperties = new HashMap<>();

    public JsonObject() {

    }

    @JsonAnyGetter
    public Map<String, Object> any() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void set(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}
