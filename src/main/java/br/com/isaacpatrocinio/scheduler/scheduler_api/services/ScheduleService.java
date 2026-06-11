package br.com.isaacpatrocinio.scheduler.scheduler_api.services;

import br.com.isaacpatrocinio.scheduler.scheduler_api.domain.entities.Schedule;
import br.com.isaacpatrocinio.scheduler.scheduler_api.repositories.ScheduleRepository;
import br.com.isaacpatrocinio.scheduler.scheduler_api.services.exceptions.ResourceNotFoundException;
import br.com.isaacpatrocinio.scheduler.scheduler_api.services.exceptions.ScheduleException;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public List<Schedule> findAllByDate(LocalDate date) {
        LocalDateTime dtStart = date.atStartOfDay();
        LocalDateTime dtEnd = date.atTime(18, 00);

        List<Schedule> allSchedulesFiltered = scheduleRepository.findByScheduleDateTimeBetween(dtStart, dtEnd);
        return allSchedulesFiltered;
    }

    public Schedule findById(Long id) {
        return scheduleRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Invalid id.")
        );
    }

    public Schedule findByPhone(String phone) {
        return scheduleRepository.findByCustomerPhone(phone).orElseThrow(
                () -> new ResourceNotFoundException("Invalid phone.")
        );
    }

    @Transactional
    public Schedule saveSchedule(Schedule schedule) {
        LocalDateTime scheduleDateTime = schedule.getScheduleDateTime();
        LocalDateTime nextAvailableScheduleDateTime = scheduleDateTime.plusHours(1);

        List<Schedule> filteredSchedules = scheduleRepository.findByServiceTypeAndScheduleDateTimeBetween(schedule.getServiceType(), scheduleDateTime, nextAvailableScheduleDateTime);

        if (!filteredSchedules.isEmpty()) {
            throw new ScheduleException("Time is already booked.");
        }

        return scheduleRepository.save(schedule);
    }

    @Transactional
    public void deleteScheduleByDateTime(LocalDateTime dateTime, String customerName) {
        scheduleRepository.deleteScheduleByScheduleDateTimeAndCustomerName(dateTime, customerName);
    }

    @Transactional
    public Schedule updateSchedule(Schedule schedule, String customerName, LocalDateTime scheduleTime) {
        List<Schedule> schedules = scheduleRepository.findByScheduleDateTimeAndCustomerName(scheduleTime, customerName);
        if (schedules.isEmpty()) {
            throw new ScheduleException("Time not booked.");
        }

        schedule.setId(schedules.getFirst().getId());
        return scheduleRepository.save(schedule);
    }

}
