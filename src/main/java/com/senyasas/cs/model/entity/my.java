package com.senyasas.cs.model.entity;


import lombok.*;
import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@With
@Getter
@Setter
@ToString
@Entity
@EqualsAndHashCode
public class my {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Version
    private Long version;

    private String text;

}
