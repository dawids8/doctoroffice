package com.doctorsoffice.schedule;

import com.doctorsoffice.doctor.Doctor;
import com.doctorsoffice.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    List<Schedule> findAllByDoctor(Doctor doctor);

}
