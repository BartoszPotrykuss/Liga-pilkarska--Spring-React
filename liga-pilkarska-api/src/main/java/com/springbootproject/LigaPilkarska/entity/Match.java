package com.springbootproject.LigaPilkarska.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "football_match")
public class Match {

    @Id
    @SequenceGenerator(
            name = "match_sequence",
            sequenceName = "match_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "match_sequence"
    )
    private Long id;
    private LocalDate date;
    private String location;

    @JsonIgnoreProperties("matches")
    @ManyToMany(
            cascade = CascadeType.MERGE
    )
    @JoinTable(
            name = "team_match_map",
            joinColumns = @JoinColumn(
                    name = "match_id",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "team_id",
                    referencedColumnName = "id"
            )
    )
    private List<Team> teams;

}
