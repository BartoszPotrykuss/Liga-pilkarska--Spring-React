package com.springbootproject.LigaPilkarska.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Team {

    @Id
    @SequenceGenerator(
            name = "team_sequence",
            sequenceName = "team_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "team_sequence"
    )
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String country;

    @JsonIgnoreProperties("team")
    @OneToOne(
            mappedBy = "team"
    )
    private Coach coach;

    @JsonIgnoreProperties("team")
    @OneToMany(
            mappedBy = "team",
            cascade = CascadeType.ALL
    )
    private List<Player> players;

    @ManyToMany(mappedBy = "teams")
    private List<Match> matches;
}
