package com.doctorsoffice.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    boolean existsPatientByPesel(String pesel);

    boolean existsPatientByPhoneNumber(String phoneNumber);

    boolean existsPatientByEmail(String email);

}
