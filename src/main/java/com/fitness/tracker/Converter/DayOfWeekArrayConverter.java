package com.fitness.tracker.Converter;

import com.fitness.tracker.Enum.DayOfWeek;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Arrays;
import java.util.stream.Collectors;

@Converter
public class DayOfWeekArrayConverter implements AttributeConverter<DayOfWeek[], String> {

    @Override
    public String convertToDatabaseColumn(DayOfWeek[] attribute) {
        if (attribute == null || attribute.length == 0) return "";
        return Arrays.stream(attribute)
                .map(DayOfWeek::name)
                .collect(Collectors.joining(","));
    }

    @Override
    public DayOfWeek[] convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isEmpty()) return new DayOfWeek[0];
        return Arrays.stream(dbData.split(","))
                .map(DayOfWeek::valueOf)
                .toArray(DayOfWeek[]::new);
    }
}
