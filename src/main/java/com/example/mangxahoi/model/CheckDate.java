package com.example.mangxahoi.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class CheckDate {
    public static LocalDateTime getTimePost() {
        LocalDateTime today = LocalDateTime.of(LocalDate.now(), LocalTime.now());
        return today;
    }

}
