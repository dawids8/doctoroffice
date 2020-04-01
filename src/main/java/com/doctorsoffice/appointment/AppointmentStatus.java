package com.doctorsoffice.appointment;

public enum AppointmentStatus {

    /**
     * Dostępna do zapisu
     */
    AVAILABLE,
    /**
     * Nie odbyła się, z powodu braku rejestracji
     */
    EXPIRED,
    /**
     * Zarezerwowana
     */
    BOOKED,
    /**
     * Nie odbyła się, ponieważ pacjent nie przyszedł
     */
    NOT_COMPLETED,
    /**
     * Zakończona
     */
    COMPLETED

}
