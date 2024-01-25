package com.springbootproject.LigaPilkarska.repository;

import com.springbootproject.LigaPilkarska.entity.Match;
import com.springbootproject.LigaPilkarska.entity.Player;
import com.springbootproject.LigaPilkarska.entity.Team;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MatchRepositoryTest {

    @Autowired
    private MatchRepository matchRepository;

    @Test
    public void saveMatch() {
        Team team1 = Team.builder()
            .name("Lechia Gdansk")
            .country("Poland")
            .build();

        Team team2 = Team.builder()
                .name("FC Barcelona")
                .country("Spain")
                .build();

        Match match = Match.builder()
                .date(LocalDate.now())
                .location("Camp Nou")
                .teams(new HashSet<>())
                .build();

        match.getTeams().add(team1);
        match.getTeams().add(team2);

        matchRepository.save(match);
    }

}