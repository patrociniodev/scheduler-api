package br.com.isaacpatrocinio.scheduler.scheduler_api.services;

import br.com.isaacpatrocinio.scheduler.scheduler_api.domain.entities.Schedule;
import br.com.isaacpatrocinio.scheduler.scheduler_api.repositories.ScheduleRepository;
import br.com.isaacpatrocinio.scheduler.scheduler_api.services.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public List<Schedule> findAll() {
        return scheduleRepository.findAll();
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
}
