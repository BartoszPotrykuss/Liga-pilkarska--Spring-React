package com.springbootproject.LigaPilkarska.service;

import com.springbootproject.LigaPilkarska.entity.Player;
import com.springbootproject.LigaPilkarska.repository.PlayerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class PlayerService {

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
}
