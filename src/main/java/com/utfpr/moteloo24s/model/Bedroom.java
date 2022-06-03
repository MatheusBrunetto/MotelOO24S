package com.utfpr.moteloo24s.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bedroom {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_id_bedroom")
    @SequenceGenerator(name = "sequence_id_bedroom", sequenceName = "sequence_bedroom")
    private long id;
    @ManyToOne
    private BedroomType bedroomType;
    private int bedroomNumber;
    private Status status;
}
