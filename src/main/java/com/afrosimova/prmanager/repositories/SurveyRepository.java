package com.afrosimova.prmanager.repositories;

import com.afrosimova.prmanager.entities.Survey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SurveyRepository extends JpaRepository<Survey, Long> {
}
