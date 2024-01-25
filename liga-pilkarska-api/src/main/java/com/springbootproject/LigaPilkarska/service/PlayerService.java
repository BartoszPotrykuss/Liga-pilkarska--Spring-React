package com.springbootproject.LigaPilkarska.service;

import com.springbootproject.LigaPilkarska.entity.Player;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PlayerService {
    Player savePlayer(Player player);

    List<Player> getPlayers();

    void deletePlayerById(Long id);

    Player updatePlayer(Long id, Player player);
}
