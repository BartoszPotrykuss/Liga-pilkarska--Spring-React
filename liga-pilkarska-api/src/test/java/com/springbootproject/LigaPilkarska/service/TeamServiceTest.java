package com.springbootproject.LigaPilkarska.service;

import com.springbootproject.LigaPilkarska.entity.Team;
import com.springbootproject.LigaPilkarska.repository.TeamRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TeamServiceTest {
    
    @Autowired
    private TeamService teamService;
    
    @MockBean
    private TeamRepository teamRepository;

    @BeforeEach
    void setUp() {
        Team team =
                Team.builder()
                        .id(1L)
                        .name("Lechia Gdansk")
                        .country("Poland")
                        .build();
        Mockito.when(teamRepository.findById(1L))
                .thenReturn(Optional.of(team)); // Zmiana na Optional.of(team)
    }

    @Test
    @DisplayName("Get data based on Team id")
    public void whenValidId_thenTeamShouldFound() {
        Long id = 1L;
        Optional<Team> foundOptional = teamRepository.findById(id); // Zmiana na Optional<Team>

        // Sprawdzenie czy obiekt Optional zawiera Team
        Team found = foundOptional.orElse(null);
        assertEquals(id, found.getId());
    }

}
