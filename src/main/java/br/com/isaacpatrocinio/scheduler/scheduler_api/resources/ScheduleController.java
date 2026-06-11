package br.com.isaacpatrocinio.scheduler.scheduler_api.resources;

import br.com.isaacpatrocinio.scheduler.scheduler_api.domain.entities.Schedule;
import br.com.isaacpatrocinio.scheduler.scheduler_api.services.ScheduleService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.catalina.connector.Response;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @GetMapping
    public ResponseEntity<List<Schedule>> findAllByDate(@RequestParam LocalDateTime dateTime) {
        List<Schedule> list = scheduleService.findAllByDate(dateTime.toLocalDate());
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

    @PostMapping
    public ResponseEntity<Schedule> save(@RequestBody Schedule schedule, HttpServletRequest request) {
        Schedule obj = scheduleService.saveSchedule(schedule);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path(request.getRequestURI()).buildAndExpand().toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @PutMapping
    public ResponseEntity<Schedule> update(@RequestBody Schedule schedule, @RequestParam String customerName, @RequestParam LocalDateTime scheduleTime) {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.updateSchedule(schedule, customerName, scheduleTime));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteByDateTime(@RequestParam LocalDateTime dateTime, @RequestParam String customerName) {
        scheduleService.deleteScheduleByDateTime(dateTime, customerName);
        return ResponseEntity.noContent().build();
    }
}
