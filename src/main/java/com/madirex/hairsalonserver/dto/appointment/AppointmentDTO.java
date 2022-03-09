package com.madirex.hairsalonserver.dto.appointment;

import com.madirex.hairsalonserver.dto.service.ServiceDTO;
import com.madirex.hairsalonserver.dto.user.UserDTO;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class AppointmentDTO {
    private String id;
    @NotNull(message = "La fecha no puede ser nula")
    private LocalDate date;
    @NotNull(message = "El tiempo no puede ser nulo")
    private LocalTime time;
    @NotNull(message = "El usuario no puede ser nulo")
    private UserDTO user;
    @NotNull(message = "El servicio no puede ser nulo")
    private ServiceDTO service;
}