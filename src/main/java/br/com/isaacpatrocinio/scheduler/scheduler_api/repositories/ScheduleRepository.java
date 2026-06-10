package br.com.isaacpatrocinio.scheduler.scheduler_api.repositories;

import br.com.isaacpatrocinio.scheduler.scheduler_api.domain.entities.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    Optional<Schedule> findByCustomerPhone(String customerPhone);
}
