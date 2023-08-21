package com.bettorleague.microservice.common.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.time.Instant;

import static com.bettorleague.microservice.common.jackson.ObjectMapperConfig.DATE_TIME_FORMATTER;

public class InstantSerializer extends StdSerializer<Instant> {
    public InstantSerializer() {
        this(null);
    }

    public InstantSerializer(Class<Instant> t) {
        super(t);
    }

    @Override
    public void serialize(Instant value,
                          JsonGenerator gen,
                          SerializerProvider arg2) throws IOException {
        gen.writeString(DATE_TIME_FORMATTER.format(value));
    }
}

