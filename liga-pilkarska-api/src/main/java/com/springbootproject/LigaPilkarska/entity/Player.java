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
public class Player {

    @Id
    @SequenceGenerator(
            name = "player_sequence",
            sequenceName = "player_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "player_sequence"
    )
    private Long id;
    private String name;
    private String position;

    @JsonIgnoreProperties({"coach", "coach", "players","country", "matches"})
    @ManyToOne(
            cascade = CascadeType.MERGE
    )
    @JoinColumn(
            name = "team_id",
            referencedColumnName = "id"
    )
    private Team team;
}
