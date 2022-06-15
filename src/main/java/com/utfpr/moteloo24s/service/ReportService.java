package com.utfpr.moteloo24s.service;

import com.utfpr.moteloo24s.model.TotalLocation;
import com.utfpr.moteloo24s.model.TotalLocationType;

import java.sql.Date;
import java.util.List;

public interface ReportService {

    public abstract List<TotalLocation> totalLocation(Date inicialDate, Date finalDate);

    public abstract List<TotalLocationType> totalLocationType(Date inicialDate, Date finalDate, Long type);
}
