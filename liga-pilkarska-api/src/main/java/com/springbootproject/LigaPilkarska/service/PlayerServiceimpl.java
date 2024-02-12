package com.springbootproject.LigaPilkarska.service;

import com.springbootproject.LigaPilkarska.entity.Player;
import com.springbootproject.LigaPilkarska.entity.Team;
import com.springbootproject.LigaPilkarska.repository.PlayerRepository;
import com.springbootproject.LigaPilkarska.repository.TeamRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class PlayerServiceimpl implements PlayerService{

    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;

    public PlayerServiceimpl(PlayerRepository playerRepository, TeamRepository teamRepository) {
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
    }
    @Override
    public Player savePlayer(Player player) {
        return playerRepository.save(player);
    }

    @Override
    public List<Player> getPlayers() {
        return playerRepository.findAll();
    }

    @Override
    public void deletePlayerById(Long id) {
        playerRepository.deleteById(id);
    }

    @Override
    public Player updatePlayer(Long id, Player player) {

        Player playerDB = playerRepository.findById(id).orElse(null);

        if(playerDB != null) {
            if(Objects.nonNull(player.getName()) && !"".equalsIgnoreCase(player.getName())) {
                playerDB.setName(player.getName());
            }
            if(Objects.nonNull(player.getPosition()) && !"".equalsIgnoreCase(player.getPosition())) {
                playerDB.setPosition(player.getPosition());
            }

            Team newTeam = player.getTeam();

            if(newTeam != null) {
                Team existingTeam = playerDB.getTeam();
                if(existingTeam == null || !existingTeam.getId().equals(newTeam.getId())) {
                    Team updatedTeam = teamRepository.findById(newTeam.getId()).orElse(null);
                    playerDB.setTeam(updatedTeam);
                }
            }
            return  playerRepository.save(playerDB);
        }
        return null;
    }

    @Override
    public List<Player> getPlayersByTeamId(Long id) {
        return playerRepository.findByTeamId_Id(id);
    }

}
