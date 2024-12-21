package com.iot.demo.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@Data
@Builder
public class HourData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private float energy;
    private int hour;
    private LocalDate date;

    public HourData(){
        this.hour = LocalDateTime.now().getHour();
        this.date = LocalDateTime.now().toLocalDate();
    }

    public int getHour() {
        return hour;
    }
    public void setHour(int hour) {
        this.hour = hour;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public float getEnergy() {
        return energy;
    }
    public void setEnergy(float energy) {
        this.energy = energy;
    }


}
