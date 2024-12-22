package com.iot.demo.controller;

import com.iot.demo.DTO.DayDataDTO;
import com.iot.demo.DTO.EnergyDataDTO;
import com.iot.demo.DTO.HourDataDTO;
import com.iot.demo.EnergyWebSocketHandler;
import com.iot.demo.service.DayDataService;
import com.iot.demo.service.HourDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/energy")
public class EnergyDataController {
    @Autowired
    private EnergyWebSocketHandler webSocketHandler;

    @Autowired
    private HourDataService hourDataService;

    @Autowired
    private DayDataService dayDataService;

    @PostMapping("/simulate")
    public void simulateEnergyData(@RequestBody EnergyDataDTO energyDataDTO) {
        String energyDataJson = "Real time:{\"voltage\": " + energyDataDTO.getVoltage() +
                ", \"current\": " + energyDataDTO.getCurrent() +
                ", \"power\": " + energyDataDTO.getPower() + "}";
        webSocketHandler.sendEnergyData(energyDataJson);
    }

    @PostMapping("update")
    public void updateEnergyData(@RequestBody Float newenergy) {
        hourDataService.updateHourData(newenergy);
        System.out.println(hourDataService.getHourData());
        webSocketHandler.sendEnergyData(hourDataService.getHourData());
    }

    @GetMapping("gethourdata")
    public List<HourDataDTO> getHourData() {
        return hourDataService.getHourDataDTO();
    }

    @GetMapping("getdaydata")
    public List<DayDataDTO> getDayData() {
        return dayDataService.getDayData();
    }

}
