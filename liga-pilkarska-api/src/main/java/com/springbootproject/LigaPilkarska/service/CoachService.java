package com.springbootproject.LigaPilkarska.service;

import com.springbootproject.LigaPilkarska.entity.Coach;
import com.springbootproject.LigaPilkarska.repository.CoachRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


public interface CoachService {


    Coach saveCoach(Coach coach);

    List<Coach> getCoaches();

    void deleteCoachById(Long id);

    Coach updateCoach(Long id, Coach coach);
}
