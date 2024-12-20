package com.iot.demo.controller;

import com.iot.demo.DTO.EnergyDataDTO;
import com.iot.demo.EnergyWebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/energy")
public class EnergyDataController {
    @Autowired
    private EnergyWebSocketHandler webSocketHandler;

    @PostMapping("/simulate")
    public void simulateEnergyData(@RequestBody EnergyDataDTO energyDataDTO) {
        System.out.println("ok");
        String energyDataJson = "{\"voltage\": " + energyDataDTO.getVoltage() +
                ", \"current\": " + energyDataDTO.getCurrent() +
                ", \"power\": " + energyDataDTO.getPower() + "}";
        System.out.println(energyDataJson);
        webSocketHandler.sendEnergyData(energyDataJson);
    }




}
