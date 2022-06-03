package com.utfpr.moteloo24s.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocationItem {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_id_location_item")
    @SequenceGenerator(name = "sequence_id_location_item", sequenceName = "sequence_location_item")
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
