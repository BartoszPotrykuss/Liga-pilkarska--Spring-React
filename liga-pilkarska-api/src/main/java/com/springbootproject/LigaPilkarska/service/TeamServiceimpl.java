package com.springbootproject.LigaPilkarska.service;

import com.springbootproject.LigaPilkarska.entity.Team;
import com.springbootproject.LigaPilkarska.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TeamServiceimpl implements TeamService {

    @Autowired
    private TeamRepository teamRepository;

    @Override
    public Team saveTeam(Team team) {
        return teamRepository.save(team);
    }

    @Override
    public List<Team> getTeamList() {
        return teamRepository.findAll();
    }

    @Override
    public Team getTeamById(Long id){
        return teamRepository.findById(id).get();
    }

    @Override
    public void deleteTeamById(Long id) {
        teamRepository.deleteById(id);
    }

    @Override
    public Team updateTeam(Long id, Team team) {
        Team teamDB = teamRepository.findById(id).get();

        if(Objects.nonNull(team.getName()) && !"".equalsIgnoreCase(team.getName())) {
            teamDB.setName(team.getName());
        }

        if(Objects.nonNull(team.getCountry()) && !"".equalsIgnoreCase(team.getCountry())) {
            teamDB.setCountry(team.getCountry());
        }

        return teamRepository.save(teamDB);
    }


}
