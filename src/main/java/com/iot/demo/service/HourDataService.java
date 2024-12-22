package com.iot.demo.service;

import com.iot.demo.DTO.HourDataDTO;
import com.iot.demo.entity.DayData;
import com.iot.demo.entity.HourData;
import com.iot.demo.repository.DayDataRepo;
import com.iot.demo.repository.HourDataRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class HourDataService {
    private  MyMapper mapper;

    @Autowired
    private HourDataRepo hourDataRepo;
    @Autowired
    private DayDataRepo dayDataRepo;

    public String getHourData(){
        String hourDataString = "Hour Data:{";
        LocalDate today = LocalDateTime.now().toLocalDate();
        List<HourData> hourData = hourDataRepo.findHourDataByDate(today);

        for (int i = 0; i < hourData.size(); i++) {
            HourData hourData1 = hourData.get(i);

            String hour = "h"+String.valueOf(hourData1.getHour());
            String value = String.valueOf(hourData1.getEnergy());

            // Add the hour-value pair to the JSON string
            hourDataString += "\"" + hour + "\": "  + value ;

            // If it's not the last element, add a comma to separate pairs
            if (i < hourData.size() - 1) {
                hourDataString += ", ";
            }
        }
        hourDataString += "}";
        return hourDataString;
    }

    public List<HourDataDTO> getHourDataDTO(){
        LocalDate today = LocalDateTime.now().toLocalDate();
        List<HourData> hourData = hourDataRepo.findHourDataByDate(today);
        List<HourDataDTO> hourDataDTOList = new ArrayList<>();
        for (int i = 0; i < hourData.size(); i++) {
            HourDataDTO hourDataDTO = new HourDataDTO();
            hourDataDTO.setHour(hourData.get(i).getHour());
            hourDataDTO.setEnergy(hourData.get(i).getEnergy());
            hourDataDTOList.add(hourDataDTO);
        }
        return hourDataDTOList;
    }

    public void updateHourData(float newEnergy){
        int hour = LocalDateTime.now().getHour();
        LocalDate date = LocalDateTime.now().toLocalDate();
        HourData hourData = hourDataRepo.findHourDataByHourAndDate(hour, date);
        DayData dayData = dayDataRepo.findDayDataByDate(date);

        if (dayData == null) {
            dayData = new DayData();
            dayData.setDate(date);
            dayData.setEnergy(newEnergy);
        }else {
            float currentEnergy = dayData.getEnergy();
            float newEnergy1 = currentEnergy + newEnergy;
            dayData.setEnergy(newEnergy1);
        }

        if (hourData == null) {
            hourData = new HourData();
            hourData.setEnergy(newEnergy);
        }else{
            float currentEnergy = hourData.getEnergy();
            float newEnergy1 = currentEnergy + newEnergy;
            hourData.setEnergy(newEnergy1);
        }

        hourDataRepo.save(hourData);
        dayDataRepo.save(dayData);
    }
}
