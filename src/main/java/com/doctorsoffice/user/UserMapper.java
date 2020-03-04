package com.doctorsoffice.user;

import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDto toDto(User user) {
        return new UserDto(user.getId(), user.getUsername(), user.getFirstname(), user.getLastname(),
                user.getDateOfBirth(), user.getEmail(), user.getPhoneNumber());
    }

    public User fromDto(UserDto userDto) {
        return new User(userDto.getId(), userDto.getUsername(), userDto.getPassword(), userDto.getFirstname(),
                userDto.getLastname(), userDto.getDateOfBirth(), userDto.getEmail(), userDto.getPhoneNumber());
    }
}
