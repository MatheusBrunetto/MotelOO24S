package com.utfpr.moteloo24s.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Location {
    @Id
    @GeneratedValue
    @Type(type = "uuid-char")
    private UUID id;
    @ManyToOne
    private Bedroom bedroom;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private PeriodType periodType;
    private Situation situation;
    private double totalValue;

    @OneToMany(mappedBy = "location")
    private List<LocationItem> locationItems;
}
