package org.catncode.leaders_backend.core.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Configuration
public class JacksonConfig {
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jsonCustomizer() {
        return builder -> builder
                .serializerByType(LocalDate.class, new JsonSerializer<LocalDate>() {
                    @Override
                    public void serialize(
                            LocalDate localDate, JsonGenerator jsonGenerator, SerializerProvider serializerProvider
                    ) throws IOException {
                        jsonGenerator.writeString(localDate.format(DateTimeFormatter.ISO_LOCAL_DATE));
                    }
                })
                .serializerByType(LocalTime.class, new JsonSerializer<LocalTime>() {
                    @Override
                    public void serialize(
                            LocalTime localTime, JsonGenerator jsonGenerator, SerializerProvider serializerProvider
                    ) throws IOException {
                        jsonGenerator.writeString(localTime.format(DateTimeFormatter.ISO_LOCAL_TIME));
                    }
                })
                .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }
}
