package com.springbootproject.LigaPilkarska.controller;

import com.springbootproject.LigaPilkarska.entity.Team;
import com.springbootproject.LigaPilkarska.service.TeamService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class TeamController {

    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @PostMapping("/team")
    public Team saveTeam(@Valid @RequestBody Team team) {
        return teamService.saveTeam(team);
    }

    @GetMapping("/team")
    public List<Team> getTeamList() {
        return teamService.getTeamList();
    }

    @GetMapping("/team/{id}")
    public Team getTeamById(@PathVariable("id") Long id) {
        return teamService.getTeamById(id);
    }

    @DeleteMapping("/team/{id}")
    public String deleteTeamById(@PathVariable("id") Long id) {
        teamService.deleteTeamById(id);
        return "Successful deletion";
    }

    @PutMapping("/team/{id}")
    public Team updateTeam(@PathVariable("id") Long id, @RequestBody Team team) {
        return teamService.updateTeam(id, team);
    }
}
