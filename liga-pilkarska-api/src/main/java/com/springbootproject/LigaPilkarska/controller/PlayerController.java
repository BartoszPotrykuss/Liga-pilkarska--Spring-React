package com.springbootproject.LigaPilkarska.controller;

import com.springbootproject.LigaPilkarska.entity.Player;
import com.springbootproject.LigaPilkarska.service.PlayerService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }
    @PostMapping("/player")
    public Player savePlayer(@Valid @RequestBody Player player) {
        return playerService.savePlayer(player);
    }

    @GetMapping("/player")
    public List<Player> getPlayers() {
        return playerService.getPlayers();
    }

    @GetMapping("/player/team/id/{id}")
    public List<Player> getPlayersByTeamId(@PathVariable("id") Long id) {
        return playerService.getPlayersByTeamId(id);
    }


    @DeleteMapping("/player/{id}")
    public String deletePlayerById(@PathVariable("id") Long id) {
        playerService.deletePlayerById(id);
        return "Successful deletion";
    }

    @PutMapping("/player/{id}")
    public Player updatePlayer(@PathVariable("id") Long id, @RequestBody Player player) {
        return playerService.updatePlayer(id, player);
    }
}
