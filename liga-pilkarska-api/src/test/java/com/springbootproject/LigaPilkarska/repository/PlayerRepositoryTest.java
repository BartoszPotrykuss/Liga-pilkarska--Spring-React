package com.springbootproject.LigaPilkarska.repository;

import com.springbootproject.LigaPilkarska.entity.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PlayerRepositoryTest {

    @Autowired
    private PlayerRepository playerRepository;


    @Test
    public void PlayerRepository_GetAll_ReturnMoreThanOnePlayer() {
        //Arrange
        Player player1 = Player.builder()
                .name("Yamal")
                .position("attacker")
                .build();
        Player player2 = Player.builder()
                .name("Araujo")
                .position("defender")
                .build();

        //Act
        playerRepository.save(player1);
        playerRepository.save(player2);

        //Assert
        List<Player> players = playerRepository.findAll();
        assertThat(players).isNotNull();
        assertThat(players.size()).isGreaterThan(1);

    }

    @Test
    public void PlayerRepository_SaveAll_ReturnSavedTeam() {
        //Arrange
        Player player1 = Player.builder()
                .name("Yamal")
                .position("attacker")
                .build();

        //Act
        Player insertedPlayer = playerRepository.save(player1);

        //Assert
        assertThat(insertedPlayer).isNotNull();
        assertThat(insertedPlayer.getId()).isGreaterThan(0);
    }

    @Test
    public void PlayerRepository_FindById_ReturnTeam() {
        //Arrange
        Player player1 = Player.builder()
                .name("Yamal")
                .position("attacker")
                .build();
        //Act
        Player insertedPlayer = playerRepository.save(player1);

        //Assert
        Player savedPlayer = playerRepository.findById(player1.getId()).get();

        assertThat(savedPlayer).isNotNull();
    }

    @Test
    public void PlayerRepository_UpdateTeam_ReturnInsertedPlayerNameAndPositionIsEqualSavedPlayerNameAndPosition() {
        //Arrange
        Player player1 = Player.builder()
                .name("Yamal")
                .position("attacker")
                .build();
        //Act
        Player insertedPlayer = playerRepository.save(player1);

        insertedPlayer.setName("Gundogan");
        insertedPlayer.setPosition("midfielder");

        playerRepository.save(insertedPlayer);

        //Assert
        Player savedPlayer = playerRepository.findById(insertedPlayer.getId()).get();
        assertThat(savedPlayer).isNotNull();
        assertThat(savedPlayer.getName()).isEqualTo(insertedPlayer.getName());
        assertThat(savedPlayer.getPosition()).isEqualTo(insertedPlayer.getPosition());
    }

    @Test
    public void PlayerRepository_Delete_ReturnPlayerIsEmpty() {
        //Arrange
        Player player1 = Player.builder()
                .name("Yamal")
                .position("attacker")
                .build();

        //Act
        playerRepository.save(player1);
        playerRepository.delete(player1);

        //Assert
        Optional<Player> deletedPlayer = playerRepository.findById(player1.getId());
        assertThat(deletedPlayer).isEmpty();
    }
}
