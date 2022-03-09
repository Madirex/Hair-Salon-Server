package com.madirex.hairsalonserver.mapper;

import com.madirex.hairsalonserver.dto.login.LoginDTO;
import com.madirex.hairsalonserver.model.Login;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class LoginMapper {
    private final ModelMapper modelMapper;

    public LoginDTO toDTO(Login login) {
        return modelMapper.map(login, LoginDTO.class);
    }

    public Login fromDTO(LoginDTO loginDTO) {
        return modelMapper.map(loginDTO, Login.class);
    }

    public List<LoginDTO> toDTO(List<Login> logins) {
        return logins.stream().map(this::toDTO).collect(Collectors.toList());
    }
}