package com.springbootproject.LigaPilkarska.repository;

import com.springbootproject.LigaPilkarska.entity.Coach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoachRepository extends JpaRepository<Coach, Long> {
}
