package com.iot.demo.service;

import com.iot.demo.DTO.DayDataDTO;
import com.iot.demo.entity.DayData;
import com.iot.demo.repository.DayDataRepo;
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
public class DayDataService {

    @Autowired
    private DayDataRepo dayDataRepo;

    public List<DayDataDTO> getDayData() {
        List<DayData> dayDataList = dayDataRepo.findAllByCurrentMonth();
        List<DayDataDTO> dayDataDTOList = new ArrayList<>();
        for (DayData dayData : dayDataList) {
            DayDataDTO dayDataDTO = new DayDataDTO();
            dayDataDTO.setDate(dayData.getDate());
            dayDataDTO.setEnergy(dayData.getEnergy());
            dayDataDTOList.add(dayDataDTO);
        }
        return dayDataDTOList;
    }
}
