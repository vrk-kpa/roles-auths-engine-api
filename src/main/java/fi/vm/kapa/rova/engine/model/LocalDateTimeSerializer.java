package fi.vm.kapa.rova.engine.model;

import java.io.IOException;
import java.time.LocalDateTime;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class LocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {
    @Override
    public void serialize(LocalDateTime value, JsonGenerator jgen,
            SerializerProvider provider) throws IOException, JsonProcessingException {
        if (value != null && value.toString().length() > 10) {
            jgen.writeString(value.toString());
        } else {
            jgen.writeString("");
        }
    }
}