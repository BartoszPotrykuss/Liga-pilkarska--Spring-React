package com.springbootproject.LigaPilkarska.repository;

import com.springbootproject.LigaPilkarska.entity.User;
import com.springbootproject.LigaPilkarska.entity.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationTokenRepository  extends JpaRepository<VerificationToken, Long> {
    VerificationToken findByToken(String token);

}
