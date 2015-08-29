package fi.vm.kapa.rova.engine.model;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy‐MM‐dd'T'HH:mm:ss.SSSZZZZZ");

    @Override
    public LocalDateTime deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        return jp.getText() != null && jp.getText().length() > 10 ? LocalDateTime.parse(jp.getText()) : null;
//        if (jp.getText().length() < 10) {
//            return null;
//        } else {    
//            return LocalDateTime.parse(jp.getText(), formatter);
//        }
    }

}
