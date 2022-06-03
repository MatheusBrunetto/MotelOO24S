package com.utfpr.moteloo24s.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_id_item")
    @SequenceGenerator(name = "sequence_id_item", sequenceName = "sequence_item")
    private long id;
    private String description;
    private double value;
}
