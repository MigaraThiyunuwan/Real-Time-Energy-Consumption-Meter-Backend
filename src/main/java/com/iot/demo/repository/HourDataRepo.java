package com.iot.demo.repository;

import com.iot.demo.entity.HourData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface HourDataRepo extends JpaRepository<HourData, Long> {
    List<HourData> findHourDataByDate(LocalDate date);
    HourData findHourDataByHourAndDate(int hour, LocalDate date);
}
