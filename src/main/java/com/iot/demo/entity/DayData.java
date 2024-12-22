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
@NoArgsConstructor
@Data
@Builder
public class DayData {
    @Id
    private LocalDate date;
    private float energy;

    public void setDate(LocalDate date) {
        this.date = date;
    }
    public void setEnergy(float energy) {
        this.energy = energy;
    }
    public LocalDate getDate() {
        return date;
    }
    public float getEnergy() {
        return energy;
    }
}
