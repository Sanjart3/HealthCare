package com.example.healthcare.dto;

import jakarta.persistence.ColumnResult;
import jakarta.persistence.ConstructorResult;
import jakarta.persistence.SqlResultSetMapping;
import lombok.Data;
import java.time.LocalDate;

@Data
@SqlResultSetMapping(
        name = "AppointmentDtoMapping",
        classes = @ConstructorResult(
                targetClass = com.example.healthcare.dto.AppoitmentDto.class,
                columns = {
                        @ColumnResult(name = "doctor_schedule_id", type = Long.class),
                        @ColumnResult(name = "doctorFirstName", type = String.class),
                        @ColumnResult(name = "doctorLastName", type = String.class),
                        @ColumnResult(name = "schedule_day", type = LocalDate.class),
                        @ColumnResult(name = "start_time", type = String.class),
                        @ColumnResult(name = "end_time", type = String.class),
                        @ColumnResult(name = "bookingPrice", type = Long.class),
                        @ColumnResult(name = "is_available", type = Boolean.class)
                }
        )
)
public class AppoitmentDto {
    private Long scheduleId;
    private String doctorFirstName;
    private String doctorLastname;
    private LocalDate scheduleDay;
    private String startTime;
    private String endTime;
    private Long bookingPrice;
    private Boolean isAvailable;

    public AppoitmentDto(Long scheduleId, String doctorFirstName, String doctorLastname, LocalDate scheduleDay, String startTime, String endTime, Long bookingPrice, Boolean isAvailable) {
        this.scheduleId = scheduleId;
        this.doctorFirstName = doctorFirstName;
        this.doctorLastname = doctorLastname;
        this.scheduleDay = scheduleDay;
        this.startTime = startTime;
        this.endTime = endTime;
        this.bookingPrice = bookingPrice;
        this.isAvailable = isAvailable;
    }
}
