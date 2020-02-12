package com.doctorsoffice.user;

import lombok.Data;
import org.springframework.web.bind.annotation.*;

@Data
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserMapper userMapper;
    private final UserService userService;

    public UserController(UserMapper userMapper, UserService userService) {
        this.userMapper = userMapper;
        this.userService = userService;
    }

    @PostMapping("/create")
    public UserDto createUser(@RequestBody UserDto userDto) {
        final User user = userMapper.fromDto(userDto);
        userService.createUser(user);
        return userMapper.toDto(user);
    }

    @PostMapping("/login")
    public void login(@RequestParam String username, @RequestParam String password) {
        userService.login(username, password);
    }
}
