package com.madirex.hairsalonserver.config.security.jwt.model;

import com.madirex.hairsalonserver.dto.user.UserDTO;
import com.madirex.hairsalonserver.model.UserGender;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class JwtUserResponse extends UserDTO {
    @NotNull(message = "El token no puede ser nulo")
    private String token;

    @Builder(builderMethodName = "jwtUserResponseBuilder")
    public JwtUserResponse(String id, String username, String image, String name, String surname,
                           String phoneNumber, String email, UserGender gender, Set<String> userRoles, String token) {
        super(id, image, username, name, surname, phoneNumber, email, gender, userRoles);
        this.token = token;
    }
}
