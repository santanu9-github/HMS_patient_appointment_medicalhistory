package com.hms.payload;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class AppointmentDto {
    private LocalDateTime localDateTime;
    private String reasonForVisit;
}
