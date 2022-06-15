package com.utfpr.moteloo24s.controller;

import com.utfpr.moteloo24s.model.TotalLocation;
import com.utfpr.moteloo24s.model.TotalLocationType;
import com.utfpr.moteloo24s.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("report")
public class ReportController {

    private final ReportService reportService;

    @GetMapping("total-location")
    public List<TotalLocation> totalLocation(@RequestParam Date inicialDate, @RequestParam Date finalDate) {
        return reportService.totalLocation(inicialDate, finalDate);
    }

    @GetMapping("total-location-type")
    public List<TotalLocationType> totalLocationType(@RequestParam Date inicialDate, @RequestParam Date finalDate, @RequestParam Long type) {
        return reportService.totalLocationType(inicialDate, finalDate, type);
    }
}
