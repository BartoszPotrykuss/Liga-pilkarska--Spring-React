package com.springbootproject.LigaPilkarska.controller;

import com.springbootproject.LigaPilkarska.entity.Match;
import com.springbootproject.LigaPilkarska.service.MatchService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MatchController {

    @Autowired
    private MatchService matchService;

    @PostMapping("/match")
    public Match saveMatch(@Valid @RequestBody Match match) {
        return matchService.saveMatch(match);
    }


    @GetMapping("/match")
    public List<Match> getMatches() {
        return matchService.getMatches();
    }

    @DeleteMapping("/match/{id}")
    public String deleteMatchById(@PathVariable("id") Long id) {
        matchService.deleteMatchById(id);
        return "Successful deletion";
    }

    @PutMapping("/match/{id}")
    public Match updateMatch(@PathVariable("id") Long id,@RequestBody Match match) {
        return matchService.updateMatch(id, match);
    }
}
