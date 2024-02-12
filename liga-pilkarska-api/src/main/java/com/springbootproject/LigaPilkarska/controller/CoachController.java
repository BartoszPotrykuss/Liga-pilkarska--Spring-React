package com.springbootproject.LigaPilkarska.controller;

import com.springbootproject.LigaPilkarska.entity.Coach;
import com.springbootproject.LigaPilkarska.entity.Team;
import com.springbootproject.LigaPilkarska.service.CoachService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CoachController {

    private final CoachService coachService;

    public CoachController(CoachService coachService) {
        this.coachService = coachService;
    }

    @PostMapping("/coach")
    public Coach saveCoach(@Valid @RequestBody Coach coach) {
        return coachService.saveCoach(coach);
    }

    @GetMapping("/coach")
    public List<Coach> getCoaches()
    {
        return coachService.getCoaches();
    }

    @DeleteMapping("/coach/{id}")
    public String deleteCoachById(@PathVariable("id") Long id) {
        coachService.deleteCoachById(id);
        return "Successful deletion";
    }

    @PutMapping("/coach/{id}")
    public Coach updateCoach(@PathVariable("id") Long id, @RequestBody Coach coach) {
        return coachService.updateCoach(id, coach);
    }
}
