package com.springbootproject.LigaPilkarska.repository;

import com.springbootproject.LigaPilkarska.entity.Player;
import com.springbootproject.LigaPilkarska.entity.Team;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PlayerRepositoryTest {

    @Autowired
    private PlayerRepository playerRepository;

    @Test
    public void savePlayer() {
        Team team = Team.builder()
                .name("Manchester United")
                .country("England")
                .build();

        Player player = Player.builder()
                .name("Harry Maguire")
                .position("defender")
                .team(team)
                .build();

        playerRepository.save(player);
    }
}