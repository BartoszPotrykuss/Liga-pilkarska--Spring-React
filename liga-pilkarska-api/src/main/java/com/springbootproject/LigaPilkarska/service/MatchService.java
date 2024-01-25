package com.springbootproject.LigaPilkarska.service;

import com.springbootproject.LigaPilkarska.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatchService {
    List<Match> getMatches();

    Match saveMatch(Match match);

    void deleteMatchById(Long id);

    Match updateMatch(Long id, Match match);
}
