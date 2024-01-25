package com.springbootproject.LigaPilkarska.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Coach {

    @Id
    @SequenceGenerator(
            name = "coach_sequence",
            sequenceName = "coach_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "coach_sequence"
    )
    private Long id;
    private String firstName;
    private String lastName;


    @JsonIgnoreProperties("coach")
    @OneToOne(
            cascade = CascadeType.MERGE
    )
    @JoinColumn(
            name = "team_id",
            referencedColumnName = "id"
    )
    private Team team;
}
