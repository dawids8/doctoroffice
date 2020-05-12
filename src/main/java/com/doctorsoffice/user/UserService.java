package com.doctorsoffice.user;

import com.doctorsoffice.patient.Patient;
import lombok.Data;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
@Data
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void create(User user) {
        userRepository.save(user);
    }

    @Transactional
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

    private void validatePatient(User user) {
        final List<Boolean> list = Arrays.asList(userRepository.existsPatientByPesel(user.getPesel()),
                userRepository.existsPatientByPhoneNumber(user.getPhoneNumber()),
                userRepository.existsPatientByEmail(user.getEmail()));

        if (list.get(0)) {
            throw new DataIntegrityViolationException("Pesel already in use");
        } else if (list.get(1)) {
            throw new DataIntegrityViolationException("Phone number already in use");
        } else if (list.get(2)) {
            throw new DataIntegrityViolationException("Email already in use");
        }
    }

}
