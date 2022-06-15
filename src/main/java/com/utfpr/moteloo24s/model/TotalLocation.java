package com.utfpr.moteloo24s.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TotalLocation {
    private Date date;
    private Long quantity;
    private BigDecimal totalAccommodation;
    private BigDecimal totalConsumption;
}
