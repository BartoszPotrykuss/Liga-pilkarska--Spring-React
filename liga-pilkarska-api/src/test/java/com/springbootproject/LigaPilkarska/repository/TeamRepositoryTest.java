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

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TeamRepositoryTest {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private TestEntityManager entityManager;


    @Test
    void givenNewTeam_whenSave_thenSuccess() {
        Team newTeam =
                Team.builder()
                        .id(1L)
                        .name("Lechia Gdansk")
                        .country("Poland")
                        .build();
        Team insertedTeam = teamRepository.save(newTeam);
        assertThat(entityManager.find(Team.class, insertedTeam.getId()) ).isEqualTo(newTeam);
    }

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
