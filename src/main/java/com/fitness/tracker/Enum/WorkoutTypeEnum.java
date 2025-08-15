package com.fitness.tracker.Enum;

public enum WorkoutTypeEnum {
    CARDIO("Cardio"),
    STRENGTH("Strength"),
    FLEXIBILITY("Flexibility"),
    BALANCE("Balance");

    private final String type;

    WorkoutTypeEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
