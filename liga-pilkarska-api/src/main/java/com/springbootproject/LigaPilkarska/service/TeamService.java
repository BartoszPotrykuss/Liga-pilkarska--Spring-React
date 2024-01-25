package com.springbootproject.LigaPilkarska.service;

import com.springbootproject.LigaPilkarska.entity.Team;

import java.util.List;

public interface TeamService {
    Team saveTeam(Team team);

    List<Team> getTeamList();

    Team getTeamById(Long id);

    void deleteTeamById(Long id);

    Team updateTeam(Long id, Team team);
}
