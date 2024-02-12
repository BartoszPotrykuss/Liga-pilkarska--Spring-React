package com.springbootproject.LigaPilkarska.controller;

import com.springbootproject.LigaPilkarska.entity.Match;
import com.springbootproject.LigaPilkarska.service.MatchService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MatchController {

    private final MatchService matchService;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

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
