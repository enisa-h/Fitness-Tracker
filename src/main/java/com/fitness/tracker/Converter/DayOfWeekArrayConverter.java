package com.fitness.tracker.Converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.time.DayOfWeek;
import java.util.Arrays;
import java.util.stream.Collectors;

@Converter
public class DayOfWeekArrayConverter implements AttributeConverter<DayOfWeek[], String> {

    private static final String SPLIT_CHAR = ",";

    @Override
    public String convertToDatabaseColumn(DayOfWeek[] attribute) {
        if (attribute == null || attribute.length == 0) return "";
        return Arrays.stream(attribute)
                .map(DayOfWeek::name)
                .collect(Collectors.joining(SPLIT_CHAR));
    }

    @Override
    public DayOfWeek[] convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isEmpty()) return new DayOfWeek[0];
        return Arrays.stream(dbData.split(SPLIT_CHAR))
                .map(String::toUpperCase)
                .map(DayOfWeek::valueOf)
                .toArray(DayOfWeek[]::new);
    }
}
