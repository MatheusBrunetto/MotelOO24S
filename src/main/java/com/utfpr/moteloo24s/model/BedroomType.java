package com.utfpr.moteloo24s.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BedroomType {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_id_bedroom_type")
    @SequenceGenerator(name = "sequence_id_bedroom_type", sequenceName = "sequence_bedroom_type")
    private long id;
    private String description;
    private double periodValue;
    private double overnightValue;
}
