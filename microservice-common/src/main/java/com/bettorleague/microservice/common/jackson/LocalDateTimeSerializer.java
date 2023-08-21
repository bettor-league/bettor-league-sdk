package com.bettorleague.microservice.common.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.time.LocalDateTime;

import static com.bettorleague.microservice.common.jackson.ObjectMapperConfig.DATE_TIME_FORMATTER;

class LocalDateTimeSerializer extends StdSerializer<LocalDateTime> {
    public LocalDateTimeSerializer() {
        this(null);
    }

    public LocalDateTimeSerializer(Class<LocalDateTime> t) {
        super(t);
    }

    @Override
    public void serialize(LocalDateTime value,
                          JsonGenerator gen,
                          SerializerProvider arg2) throws IOException {
        gen.writeString(DATE_TIME_FORMATTER.format(value));
    }
}
