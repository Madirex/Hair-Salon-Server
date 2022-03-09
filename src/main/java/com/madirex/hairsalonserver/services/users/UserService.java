package com.madirex.hairsalonserver.services.users;

import com.madirex.hairsalonserver.dto.user.CreateUserDTO;
import com.madirex.hairsalonserver.exceptions.user.NewUserWithDifferentPasswordsException;
import com.madirex.hairsalonserver.model.User;
import com.madirex.hairsalonserver.model.UserRole;
import com.madirex.hairsalonserver.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public Optional<User> findByUsernameIgnoreCase(String username) {
        return userRepository.findByUsernameIgnoreCase(username);
    }

    public List<User> findByUsernameContainsIgnoreCase(String username) {
        return userRepository.findByUsernameContainsIgnoreCase(username);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(String id) {
        return userRepository.findById(id);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<User> findUserById(String userId) {
        return userRepository.findById(userId);
    }

    public User save(CreateUserDTO newUser) {
        if (newUser.getPassword().contentEquals(newUser.getPasswordConfirm())) {
            Set<UserRole> defaultRoles = new HashSet<>();
            defaultRoles.add(UserRole.USER);
            User user = User.builder()
                    .id(newUser.getId())
                    .image(newUser.getImage())
                    .name(newUser.getName())
                    .surname(newUser.getSurname())
                    .username(newUser.getUsername())
                    .password(passwordEncoder.encode(newUser.getPassword()))
                    .email(newUser.getEmail())
                    .phoneNumber(newUser.getPhoneNumber())
                    .image(newUser.getImage())
                    .gender(newUser.getGender())
                    .roles(defaultRoles)
                    .build();
            try {
                return userRepository.save(user);
            } catch (DataIntegrityViolationException ex) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El nombre del usuario ya existe");
            }
        } else {
            throw new NewUserWithDifferentPasswordsException();
        }
    }
}
