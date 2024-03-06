package com.springbootproject.LigaPilkarska.service;

import com.springbootproject.LigaPilkarska.entity.Player;
import com.springbootproject.LigaPilkarska.repository.PlayerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class PlayerServiceTest {

  @Mock
  private PlayerRepository playerRepository;

  @InjectMocks
    private PlayerServiceimpl playerService;

  @Test
    public void PlayerService_SavePlayer_ReturnsPlayer() {
      //Arrange
      Player player1 = Player.builder()
              .name("Yamal")
              .position("attacker")
              .build();
        when(playerRepository.save(Mockito.any(Player.class))).thenReturn(player1);

        //Act
        Player savedPlayer = playerService.savePlayer(player1);

        //Assert
        assertThat(savedPlayer).isNotNull();
  }

  @Test
  public void PlayerService_GetPlayers_ReturnsPlayers() {
    //Arrange
    List<Player> players = mock(List.class);
    when(playerRepository.findAll()).thenReturn(players);

    //Act
    List<Player> savePlayers = playerService.getPlayers();

    //Assert
    assertThat(savePlayers).isNotNull();
  }

  @Test
  public void PlayerService_UpdatePlayer_ReturnsPlayer() {
    //Arrange
    Player player1 = Player.builder()
            .name("Yamal")
            .position("attacker")
            .build();
    when(playerRepository.findById(1L))
            .thenReturn(Optional.ofNullable(player1));
    when(playerRepository.save(Mockito.any(Player.class)))
            .thenReturn(player1);

    assert  player1 != null;
    Player savedPlayer = playerService.updatePlayer(1L, player1);

    assertThat(savedPlayer).isNotNull();
  }
}
