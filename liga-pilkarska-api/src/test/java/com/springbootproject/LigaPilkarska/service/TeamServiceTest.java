package com.springbootproject.LigaPilkarska.service;

import com.springbootproject.LigaPilkarska.entity.Team;
import com.springbootproject.LigaPilkarska.repository.TeamRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class TeamServiceTest {

    @Mock
    private TeamRepository teamRepository;

    @InjectMocks
    private TeamServiceimpl teamService;


    @Test
    public void TeamService_SaveTeam_ReturnsTeam() {
        Team team =
                Team.builder()
                        .name("Lechia Gdansk")
                        .country("Poland")
                        .build();

        when(teamRepository.save(Mockito.any(Team.class)))
                .thenReturn(team);

        Team savedTeam = teamService.saveTeam(team);

        assertThat(savedTeam).isNotNull();
    }

    @Test
    public void TeamService_GetTeamList_ReturnsTeams() {
        List<Team> teams = Mockito.mock(List.class);

        when(teamRepository.findAll()).thenReturn(teams);

        List<Team> saveTeams = teamService.getTeamList();

        assertThat(saveTeams).isNotNull();
    }

    @Test
    public void TeamService_GetTeamById_ReturnsTeam() {
        Team team =
                Team.builder()
                        .name("Lechia Gdansk")
                        .country("Poland")
                        .build();

        when(teamRepository.findById(1L))
                .thenReturn(Optional.ofNullable(team));

        Team savedTeam = teamService.getTeamById(1L);

        assertThat(savedTeam).isNotNull();
    }

    @Test
    public void TeamService_UpdateTeam_ReturnsTeam() {
        Team team =
                Team.builder()
                        .name("Lechia Gdansk")
                        .country("Poland")
                        .build();

        when(teamRepository.findById(1L))
                .thenReturn(Optional.ofNullable(team));
        when(teamRepository.save(Mockito.any(Team.class)))
                .thenReturn(team);

        assert team != null;
        Team savedTeam = teamService.updateTeam(1L, team);

        assertThat(savedTeam).isNotNull();
    }
    

    //Tests from baeldung
    /*
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
                .thenReturn(Optional.of(team));
    }



    @Test
    @DisplayName("Get data based on Team id")
    public void whenValidId_thenTeamShouldFound() {
        Long id = 1L;
        Team found = teamService.getTeamById(id);

        assertEquals(id, found.getId());
    }
    */
}
