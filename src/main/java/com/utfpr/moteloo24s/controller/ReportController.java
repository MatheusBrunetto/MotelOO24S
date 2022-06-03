package com.utfpr.moteloo24s.controller;

import com.utfpr.moteloo24s.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
@RequiredArgsConstructor
@RequestMapping("report")
public class ReportController {

    private final ReportService reportService;

    @GetMapping("total-hospedagem")
    public void totalHospedagem(@RequestParam Date dataInicial, @RequestParam Date dataFinal) {

    }

    @GetMapping("total-hospedagem-tipo")
    public void totalHospedagemTipo(@RequestParam Date dataInicial, @RequestParam Date dataFinal, @RequestParam Integer tipo) {

    }
}
