package com.utfpr.moteloo24s.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LocationItem {
    @Id
    @GeneratedValue
    @Type(type = "uuid-char")
    private UUID id;
    @ManyToOne
    @JsonIgnore
    private Location location;
    @ManyToOne
    private Item item;
    private int quantity;
    private double value;

    public BigDecimal getTotalValue() {
        return BigDecimal.valueOf((quantity * value));
    }
}
