package com.doctorsoffice.user;

import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDto toDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .dateOfBirth(user.getDateOfBirth())
                .pesel(user.getPesel())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .city(user.getCity())
                .street(user.getStreet())
                .postCode(user.getPostCode())
                .userRole(user.getUserRole().toString())
                .build();
    }

    public User fromDto(UserDto userDto) {
        return User.builder()
                .id(userDto.getId())
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .firstname(userDto.getFirstname())
                .lastname(userDto.getLastname())
                .dateOfBirth(userDto.getDateOfBirth())
                .pesel(userDto.getPesel())
                .email(userDto.getEmail())
                .phoneNumber(userDto.getPhoneNumber())
                .city(userDto.getCity())
                .street(userDto.getStreet())
                .postCode(userDto.getPostCode())
                .userRole(UserRole.valueOf(userDto.getUserRole().toUpperCase()))
                .build();
    }
}
