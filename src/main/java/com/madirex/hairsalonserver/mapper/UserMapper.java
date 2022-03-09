package com.madirex.hairsalonserver.mapper;

import com.madirex.hairsalonserver.dto.user.CreateUserDTO;
import com.madirex.hairsalonserver.dto.user.UserDTO;
import com.madirex.hairsalonserver.model.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserMapper {
    private final ModelMapper modelMapper;

    public UserDTO toDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    public User fromDTO(CreateUserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }

    public User fromDTOCreate(CreateUserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }

    public List<UserDTO> toDTO(List<User> users) {
        return users.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
