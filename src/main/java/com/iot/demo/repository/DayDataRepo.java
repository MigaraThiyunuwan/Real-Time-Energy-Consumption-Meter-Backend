package com.iot.demo.repository;

import com.iot.demo.entity.DayData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface DayDataRepo extends JpaRepository<DayData, Integer> {
    DayData findDayDataByDate(LocalDate date);

    @Query("SELECT d FROM DayData d WHERE FUNCTION('MONTH', d.date) = FUNCTION('MONTH', CURRENT_DATE) AND FUNCTION('YEAR', d.date) = FUNCTION('YEAR', CURRENT_DATE)")
    List<DayData> findAllByCurrentMonth();
}
