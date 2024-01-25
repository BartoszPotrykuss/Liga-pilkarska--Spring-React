package com.springbootproject.LigaPilkarska.repository;

import com.springbootproject.LigaPilkarska.entity.Coach;
import com.springbootproject.LigaPilkarska.entity.Team;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CoachRepositoryTest {

    @Autowired
    private CoachRepository coachRepository;

    @Test
    public void saveCoach() {
        Team team = Team.builder()
                .name("Chelsea FC")
                .country("England")
                .build();

        Coach coach = Coach.builder()
                .firstName("Jan")
                .lastName("Urban")
                .team(team)
                .build();

        coachRepository.save(coach);
    }

}