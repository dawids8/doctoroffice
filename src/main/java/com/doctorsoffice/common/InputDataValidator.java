package com.doctorsoffice.common;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class InputDataValidator {

    public static void validateDates(LocalDate startDate, LocalDate endDate) {
        final boolean isFromPast = startDate.isBefore(LocalDate.now());
        if (isFromPast) {
            throw new RuntimeException("There is no way to create appointment in past.");
        }

        final boolean isBeforeStartDate = endDate.isBefore(LocalDate.now());
        if (isBeforeStartDate) {
            throw new RuntimeException("There is no way to create appointment before start date.");
        }
    }

    public static void validateDates(LocalDateTime startDate, LocalDateTime endDate) {
        final boolean isFromPast = startDate.isBefore(LocalDateTime.now());
        if (isFromPast) {
            throw new RuntimeException("There is no way to create appointment in past.");
        }

        final boolean isBeforeStartDate = endDate.isBefore(LocalDateTime.now());
        if (isBeforeStartDate) {
            throw new RuntimeException("There is no way to create appointment before start date.");
        }
    }

}
