package com.springbootproject.LigaPilkarska.repository;

import com.springbootproject.LigaPilkarska.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
}
