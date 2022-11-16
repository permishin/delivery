package com.example.deliveries.controller;

import com.example.deliveries.service.ReportService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;

@Controller
@AllArgsConstructor
@RequestMapping("/reports")
public class ReportController {

    private ReportService reportService;

    @GetMapping()
    public String getReport(Model model) {
        model.addAttribute("title", "Отчеты");
        return "report";
    }

    @PostMapping("/currentDate")
    public String currentDate(@RequestParam String startDate,
                              @RequestParam String endDate,
                              Model model) throws ParseException {
        model.addAttribute("warehouse", reportService.filterByDate(startDate, endDate));
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);
        return "report";
    }
}
