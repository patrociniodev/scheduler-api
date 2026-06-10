package br.com.isaacpatrocinio.scheduler.scheduler_api.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_schedule")
@Entity
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String serviceType;

    private String professional;

    private LocalDateTime scheduleDateTime;

    private String customerName;

    private String customerPhone;

    private LocalDateTime creationDateTime;
}
