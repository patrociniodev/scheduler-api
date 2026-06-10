package br.com.isaacpatrocinio.scheduler.scheduler_api.resources;

import br.com.isaacpatrocinio.scheduler.scheduler_api.domain.entities.Schedule;
import br.com.isaacpatrocinio.scheduler.scheduler_api.services.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @GetMapping
    public ResponseEntity<List<Schedule>> findAll() {
        List<Schedule> list = scheduleService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Schedule> findById(@PathVariable Long id) {
        Schedule obj = scheduleService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(obj);
    }

    @GetMapping("/phone/{phone}")
    public ResponseEntity<Schedule> findByPhone(@PathVariable String phone) {
        Schedule obj = scheduleService.findByPhone(phone);
        return ResponseEntity.status(HttpStatus.OK).body(obj);
    }
}
