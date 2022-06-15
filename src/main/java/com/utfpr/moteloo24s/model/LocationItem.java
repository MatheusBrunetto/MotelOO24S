package com.utfpr.moteloo24s.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocationItem {
    @Id
    @GeneratedValue
    @Type(type = "uuid-char")
    private UUID id;
    @ManyToOne
    private Location location;
    @ManyToOne
    private Item item;
    private int quantity;
    private double value;

    public BigDecimal getTotalValue() {
        return BigDecimal.valueOf((quantity * value));
    }
}
