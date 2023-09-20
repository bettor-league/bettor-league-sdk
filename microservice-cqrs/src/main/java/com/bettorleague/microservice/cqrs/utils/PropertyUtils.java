package com.bettorleague.microservice.cqrs.utils;

import lombok.experimental.UtilityClass;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@UtilityClass
public class PropertyUtils {

    public static HashMap<String, Object> convert(Properties prop) {
        final Map step1 = prop;
        Map<String, String> step2 = (Map<String, String>) step1;
        return new HashMap<>(step2);
    }
}
