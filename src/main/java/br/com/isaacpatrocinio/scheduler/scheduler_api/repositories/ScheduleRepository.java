package br.com.isaacpatrocinio.scheduler.scheduler_api.repositories;

import br.com.isaacpatrocinio.scheduler.scheduler_api.domain.entities.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    List<Schedule> findByScheduleDateTimeBetween(LocalDateTime dtAfter, LocalDateTime dtBefore);

    Optional<Schedule> findByCustomerPhone(String customerPhone);

    List<Schedule> findByServiceTypeAndScheduleDateTimeBetween(String service, LocalDateTime dtBefore, LocalDateTime dtAfter);

    List<Schedule> findByScheduleDateTimeAndCustomerName(LocalDateTime scheduleDateTime, String customerName);

    void deleteScheduleByScheduleDateTimeAndCustomerName(LocalDateTime scheduleDateTime, String customerName);
}
