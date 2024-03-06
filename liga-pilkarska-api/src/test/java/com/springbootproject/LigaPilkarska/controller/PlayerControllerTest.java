package com.springbootproject.LigaPilkarska.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springbootproject.LigaPilkarska.entity.Player;
import com.springbootproject.LigaPilkarska.entity.Team;
import com.springbootproject.LigaPilkarska.service.CustomUserDetailsService;
import com.springbootproject.LigaPilkarska.service.PlayerService;
import com.springbootproject.LigaPilkarska.service.TeamService;
import com.springbootproject.LigaPilkarska.util.JwtUtil;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(TeamController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class PlayerControllerTest {
    @MockBean
    private CustomUserDetailsService customUserDetailsService;

    @MockBean
    private JwtUtil jwtUtil;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlayerService playerService;

    private Player player;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        player = Player.builder()
                .name("Yamal")
                .position("attacker")
                .build();
    }

    @Test
    public void PlayerController_SaveTeam_ReturnOk() throws Exception {
        given(playerService.savePlayer(ArgumentMatchers.any())).willAnswer(invocation -> invocation.getArgument(0));


        ResultActions response = mockMvc.perform(post("/api/player")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(player)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$name", CoreMatchers.is(player.getName())))
                .andExpect(jsonPath("$position", CoreMatchers.is(player.getPosition())));
    }

    @Test
    public void PlayerController_GetPlayerList_ReturnPlayerList() throws Exception {

        //Arrange

        List<Player> players = new ArrayList<>();
        Player player1 = Player.builder()
                .name("Maguire")
                .position("defender")
                .build();
        Player player2 = Player.builder()
                .name("Yamal")
                .position("attacker")
                .build();
        players.add(player1);
        players.add(player2);

        when(playerService.getPlayers()).thenReturn(players);

        //Act
        ResultActions result = mockMvc.perform(get("/api/player"));


        //Assert
        result.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name").value("Maguire"))
                .andExpect(jsonPath("$[1].name").value("Yamal"));
    }

    @Test
    public void PlayerController_UpdatePlayer_ReturnPlayer() throws Exception {
        //Arrange
        when(playerService.updatePlayer(1L, player)).thenReturn(player);

        //act
        ResultActions response = mockMvc.perform(put("/api/player/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(player)));

        //assert
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(player.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.position", CoreMatchers.is(player.getPosition())));
    }

    @Test
    public void PlayerController_DeletePlayerById_ReturnString() throws Exception {
        //arrange
        doNothing().when(playerService).deletePlayerById(1L);

        ResultActions response = mockMvc.perform(delete("/api/player/1")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk());


    }

    @Test
    public void PlayerController_GetPlayersByTeamId_ReturnPlayersList() throws Exception {
        //Arrange
        Team team1 = Team.builder().id(1L).name("FC Barcelona").build();


        Player player1 = Player.builder()
                .name("Araujo")
                .position("defender")
                .team(team1)
                .build();
        Player player2 = Player.builder()
                .name("Yamal")
                .position("attacker")
                .team(team1)
                .build();
        List<Player> players = new ArrayList<>();

        players.add(player1);
        players.add(player2);


        when(playerService.getPlayersByTeamId(1L)).thenReturn(players);

        //Act
        ResultActions result = mockMvc.perform(get("/api/player/team/1")
                .contentType(MediaType.APPLICATION_JSON));

        //Asssert
        result.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", CoreMatchers.is(player1.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name", CoreMatchers.is(player2.getName())));
    }

}
