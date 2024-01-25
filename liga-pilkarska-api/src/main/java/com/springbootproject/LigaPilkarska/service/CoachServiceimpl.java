package com.springbootproject.LigaPilkarska.service;

import com.springbootproject.LigaPilkarska.entity.Coach;
import com.springbootproject.LigaPilkarska.entity.Team;
import com.springbootproject.LigaPilkarska.repository.CoachRepository;
import com.springbootproject.LigaPilkarska.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CoachServiceimpl implements CoachService {

    @Autowired
    private CoachRepository coachRepository;

    @Autowired
    private TeamRepository teamRepository;


    @Override
    public Coach saveCoach(Coach coach) {
        return coachRepository.save(coach);
    }

    @Override
    public List<Coach> getCoaches() {
        return coachRepository.findAll();
    }

    @Override
    public void deleteCoachById(Long id) {
        coachRepository.deleteById(id);
    }

    @Override
    public Coach updateCoach(Long id, Coach coach) {

        Coach coachDB = coachRepository.findById(id).orElse(null);

        if(coachDB != null) {
            if (Objects.nonNull(coach.getFirstName()) && !"".equalsIgnoreCase(coach.getFirstName())) {
                coachDB.setFirstName(coach.getFirstName());
            }

            if (Objects.nonNull(coach.getLastName()) && !"".equalsIgnoreCase(coach.getLastName())) {
                coachDB.setLastName(coach.getLastName());
            }

            Team newTeam = coach.getTeam();
            if (newTeam != null) {
                Team existingTeam = coachDB.getTeam();
                if (existingTeam == null || !existingTeam.getId().equals(newTeam.getId())) {
                    Team updatedTeam = teamRepository.findById(newTeam.getId()).orElse(null);
                    coachDB.setTeam(updatedTeam);
                }
            }
            return coachRepository.save(coachDB);
        }
        return  null;

    }
}
