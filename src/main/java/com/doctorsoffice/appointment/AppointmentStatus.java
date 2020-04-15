package com.doctorsoffice.appointment;

public enum AppointmentStatus {

    /**
     * Appointment is available to book
     */
    AVAILABLE,
    /**
     * Appointment did not take place as it was not reserved
     */
    EXPIRED,
    /**
     * Appointment is reserved
     */
    RESERVED,
    /**
     * Appointment did not take place- No show by patient
     */
    NOT_COMPLETED,
    /**
     * Appointment is finished and completed by doctor
     */
    COMPLETED

}
