package com.springbootproject.LigaPilkarska.service;

import com.springbootproject.LigaPilkarska.entity.Match;
import com.springbootproject.LigaPilkarska.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class MatchServiceimpl implements MatchService {

    @Autowired
    private MatchRepository matchRepository;

    @Override
    public List<Match> getMatches() {
        return matchRepository.findAll();
    }

    @Override
    public Match saveMatch(Match match) {
        return  matchRepository.save(match);
    }

    @Override
    public void deleteMatchById(Long id) {
        matchRepository.deleteById(id);
    }

    @Override
    public Match updateMatch(Long id, Match match) {

        Match matchDB = matchRepository.findById(id).orElse(null);

        if(matchDB != null) {
            if(Objects.nonNull(match.getDate()) && !"".equalsIgnoreCase(match.getDate().toString())) {
                matchDB.setDate(match.getDate());
            }
            if(Objects.nonNull(match.getLocation()) && !"".equalsIgnoreCase(match.getLocation())) {
                matchDB.setLocation(match.getLocation());
            }

            //update teams...

            return matchRepository.save(matchDB);
        }
        return null;
    }
}
