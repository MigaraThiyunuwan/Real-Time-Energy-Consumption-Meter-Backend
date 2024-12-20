package com.iot.demo.DTO;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnergyDataDTO {

    private float voltage;
    private float current;
    private float power;

    public EnergyDataDTO(float voltage, float current) {
        double poweFactor = 0.8;
        float powerFactorFloat = (float) poweFactor;
        this.voltage = voltage;
        this.current = current;
        this.power = current * voltage * powerFactorFloat;
    }

    // Getter for voltage
    public float getVoltage() {
        return voltage;
    }

    // Getter for current
    public float getCurrent() {
        return current;
    }

    // Getter for power
    public float getPower() {
        return power;
    }

}
