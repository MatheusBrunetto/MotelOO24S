package com.utfpr.moteloo24s.service;

import java.util.Date;

public interface ReportService {

    public abstract void totalHospedagem(Date dataInicial, Date dataFinal);

    public abstract void totalHospedagemTipo(Date dataInicial, Date dataFinal, Integer tipo);
}
