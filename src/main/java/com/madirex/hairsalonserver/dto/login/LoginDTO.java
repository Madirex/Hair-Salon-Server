package com.madirex.hairsalonserver.dto.login;

import com.madirex.hairsalonserver.dto.user.LoginUserDTO;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {
    private String id;
    @NotBlank(message = "El token no puede ser vacío")
    private String token;
    @NotNull(message = "La fecha no puede ser nula")
    private Date instance;
    @NotNull(message = "El usuario no puede ser nulo")
    private LoginUserDTO user;
}