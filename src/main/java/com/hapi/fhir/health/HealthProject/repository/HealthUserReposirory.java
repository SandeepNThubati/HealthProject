package com.hapi.fhir.health.HealthProject.repository;

import com.hapi.fhir.health.HealthProject.model.HealthUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HealthUserReposirory extends JpaRepository<HealthUser, Integer> {

}
