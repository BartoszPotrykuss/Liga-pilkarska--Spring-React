package com.springbootproject.LigaPilkarska.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springbootproject.LigaPilkarska.config.TestSecurityConfig;
import com.springbootproject.LigaPilkarska.entity.Team;
import com.springbootproject.LigaPilkarska.service.CustomUserDetailsService;
import com.springbootproject.LigaPilkarska.service.TeamService;
import com.springbootproject.LigaPilkarska.util.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TeamController.class)
@Import(TestSecurityConfig.class)
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
    void saveTeam() throws Exception {
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
}
