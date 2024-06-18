package com.example.dateLister.service;

import com.example.dateLister.dto.DateResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class DateService {

    public DateResponse getDates(String startDate, String endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate start = LocalDate.parse(startDate, formatter);
        LocalDate end = LocalDate.parse(endDate, formatter);

        LocalDate currentMonth = start.withDayOfMonth(1);
        List<String> dates = new ArrayList<>();

        while (!currentMonth.isAfter(end)) {
            LocalDate firstDayOfMonth = currentMonth;
            LocalDate lastDayOfMonth = currentMonth.withDayOfMonth(currentMonth.lengthOfMonth());

            dates.add(firstDayOfMonth.format(formatter));
            dates.add(lastDayOfMonth.format(formatter));
            currentMonth = currentMonth.plusMonths(1);
        }
        DateResponse  response = new DateResponse();
        response.setDates(dates);
        return response;
    }
}
