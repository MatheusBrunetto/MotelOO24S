package com.utfpr.moteloo24s.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TotalLocationType {
    private PeriodType periodType;
    private Long quantity;
    private BigDecimal totalValue;
}
