package com.utfpr.moteloo24s.model;

import lombok.*;

import java.math.BigDecimal;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TotalLocation {
    private Date date;
    private Long quantity;
    private BigDecimal totalAccommodation;
    private BigDecimal totalConsumption;
}
