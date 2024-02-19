package com.springbootproject.LigaPilkarska.repository;

import com.springbootproject.LigaPilkarska.entity.Player;
import com.springbootproject.LigaPilkarska.entity.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TeamRepositoryTest {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private TestEntityManager entityManager;

//Tests from yt: Teddy Smith
    @Test
    public void TeamRepository_GetAll_ReturnMoreThanOneTeam() {
        //Arrange
        Team team = Team.builder()
                .id(1L)
                .name("Lechia Gdansk")
                .country("Poland")
                .build();
        Team team2 = Team.builder()
                .id(1L)
                .name("Lechia Gdansk")
                .country("Poland")
                .build();

        //Act
        teamRepository.save(team);
        teamRepository.save(team2);

        //Assert
        List<Team> teams = teamRepository.findAll();
        assertThat(teams).isNotNull();
        assertThat(teams.size()).isEqualTo(8);

    }


    @Test
    void TeamRepository_SaveAll_ReturnSavedTeam() {

        //Arrange
        Team newTeam =
                Team.builder()
                        .id(1L)
                        .name("Lechia Gdansk")
                        .country("Poland")
                        .build();

        //Act
        Team insertedTeam = teamRepository.save(newTeam);

        //Assert
        assertThat(insertedTeam).isNotNull();
        assertThat(insertedTeam.getId()).isGreaterThan(0);
    }

    @Test
    public void TeamRepository_FindById_ReturnTeam() {
        //Arrange
        Team team = Team.builder()
                .name("Lechia Gdansk")
                .country("Poland")
                .build();

        //Act
        teamRepository.save(team);

        //Assert
        Team teams = teamRepository.findById(team.getId()).get();
        assertThat(teams).isNotNull();
    }


    @Test
    public void TeamRepository_UpdateTeam_ReturnTeamNotNull() {
        //Arrange
        Team team = Team.builder()
                .name("Lechia Gdansk")
                .country("Poland")
                .build();

        //Act
        teamRepository.save(team);

        Team teamSave = teamRepository.findById(team.getId()).get();
        teamSave.setName("Arka Gdynia");

        Team updateTeam = teamRepository.save(teamSave);

        assertThat(updateTeam.getName()).isNotNull();
        assertThat(updateTeam.getCountry()).isNotNull();

    }

    @Test
    public void TeamRepository_Delete_ReturnTeamIsEmpty() {
        //Arrange
        Team team = Team.builder()
                .name("Lechia Gdansk")
                .country("Poland")
                .build();

        //Act
        teamRepository.save(team);
        teamRepository.delete(team);

        //Assert
        Optional<Team> teamReturn = teamRepository.findById(team.getId());
        assertThat(teamReturn).isEmpty();
    }






    //Tests from baeldung
    @Test
    void givenTeamCreated_whenUpdate_thenSuccess() {
        Team newTeam =
                Team.builder()
                        .name("Lechia Gdansk")
                        .country("Poland")
                        .build();

        entityManager.persist(newTeam);
        String newName = "Arka Gdynia";
        newTeam.setName(newName);
        teamRepository.save(newTeam);
        assertThat(entityManager.find(Team.class, newTeam.getId()).getName()).isEqualTo(newName);
    }

    @Test
    void givenTeamCreated_whenFindById_thenSuccess() {
        Team newTeam =
                Team.builder()
                        .name("Lechia Gdansk")
                        .country("Poland")
                        .build();
        entityManager.persist(newTeam);
        Optional<Team> retrievedTeam = teamRepository.findById(newTeam.getId());
        assertThat(retrievedTeam).contains(newTeam);
    }

    @Test
    void givenTeamCreated_whenDeleted_thenSuccess() {
        Team newTeam =
                Team.builder()
                        .name("Lechia Gdansk")
                        .country("Poland")
                        .build();
        entityManager.persist(newTeam);

        teamRepository.delete(newTeam);
        assertThat(entityManager.find(Team.class, newTeam.getId())).isNull();
    }
}
