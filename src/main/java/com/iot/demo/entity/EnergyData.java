package com.iot.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@Data
@Builder
public class EnergyData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private float voltage;
    private float current;
    private float power;
    private LocalDateTime timestamp;

    public EnergyData(){
        this.timestamp = LocalDateTime.now();
        this.power = current * voltage;
    }
}
