package br.com.isaacpatrocinio.scheduler.scheduler_api.services.exceptions;

public class ScheduleException extends RuntimeException {

    public ScheduleException() {
    }

    public ScheduleException(String message) {
        super(message);
    }
}
