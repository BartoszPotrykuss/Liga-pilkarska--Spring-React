package com.springbootproject.LigaPilkarska.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springbootproject.LigaPilkarska.entity.Team;
import com.springbootproject.LigaPilkarska.service.CustomUserDetailsService;
import com.springbootproject.LigaPilkarska.service.TeamService;
import com.springbootproject.LigaPilkarska.util.JwtUtil;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TeamController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class TeamControllerTest {

    @MockBean
    private CustomUserDetailsService customUserDetailsService;

    @MockBean
    private JwtUtil jwtUtil;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TeamService teamService;

    private Team team;

    @Autowired
    private ObjectMapper objectMapper;


    @BeforeEach
    void setUp() {
        team = Team.builder()
                .id(1L)
                .name("Lechia Gdansk")
                .country("Poland")
                .build();
    }

    @Test
    public void TeamController_SaveTeam_ReturnOk() throws Exception {
        given(teamService.saveTeam(ArgumentMatchers.any())).willAnswer(invocation -> invocation.getArgument(0));


        ResultActions response = mockMvc.perform(post("/api/team")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(team)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(team.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.country", CoreMatchers.is(team.getCountry())));
    }


    @Test
    public void TeamController_GetTeamList_ReturnTeamList() throws Exception {
        //Arrange
        List<Team> teams = new ArrayList<>();
        Team team1 = Team.builder().name("FC Barcelona").build();
        Team team2 = Team.builder().name("Real Madryt").build();
        teams.add(team1);
        teams.add(team2);
        when(teamService.getTeamList()).thenReturn(teams);

        // Act
        ResultActions result = mockMvc.perform(get("/api/team")
                .contentType(MediaType.APPLICATION_JSON));

        // Assert
        result.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name").value("FC Barcelona"))
                .andExpect(jsonPath("$[1].name").value("Real Madryt"));
    }

    @Test
    public void TeamController_GetTeamById_ReturnTeam() throws Exception {
        when(teamService.getTeamById(1L)).thenReturn(team);


        ResultActions response = mockMvc.perform(get("/api/team/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(team)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(team.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.country", CoreMatchers.is(team.getCountry())));
    }

    @Test
    public void TeamController_UpdateTeam_ReturnTeam() throws Exception {
        when(teamService.updateTeam(1L, team)).thenReturn(team);


        ResultActions response = mockMvc.perform(put("/api/team/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(team)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(team.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.country", CoreMatchers.is(team.getCountry())));
    }

    @Test
    public void TeamController_DeleteTeamById_ReturnString() throws Exception {
        doNothing().when(teamService).deleteTeamById(1L);


        ResultActions response = mockMvc.perform(delete("/api/team/1")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk());
    }


    //Test from baeldung
    /*


    @Test
    public void saveTeam() throws Exception {
        Team inputTeam = Team.builder()
                .id(1L)
                .name("Lechia Gdansk")
                .country("Poland")
                .build();

        Mockito.when(teamService.saveTeam(inputTeam))
                .thenReturn(team);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/team")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inputTeam)))
                .andExpect(status().isOk());
    }

    @Test
    void getTeamById() throws Exception {
        Mockito.when(teamService.getTeamById(1L))
                .thenReturn(team);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/team/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(team.getName()));
    }

     */
}
