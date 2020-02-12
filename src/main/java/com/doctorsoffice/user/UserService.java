package com.doctorsoffice.user;

import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(User user) {
        // Walidacja
        userRepository.save(user);
    }

    public void login(String username, String password) {
        User user = userRepository.findByUsername(username);

        if (user != null) {
            if (user.getPassword().equals(password)) {
                System.out.println("Correct");
            } else {
                System.out.println("Wrong password");
            }

        } else {
            System.out.println("This username doesn't exist");
        }
    }

}
