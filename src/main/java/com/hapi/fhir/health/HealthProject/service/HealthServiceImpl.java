package com.hapi.fhir.health.HealthProject.service;

import com.hapi.fhir.health.HealthProject.model.HealthUser;
import com.hapi.fhir.health.HealthProject.repository.HealthUserReposirory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HealthServiceImpl  {

    @Autowired
    HealthUserReposirory healthUserReposirory;

    public void saveUser() {
        HealthUser user=new HealthUser();
        user.setId(2);
        user.setEmp_name("simon");
        user.setEmp_password("simon");
        healthUserReposirory.save(user);
       // user =healthUserReposirory.getOne(1);
       System.out.println("sabved to database");
    }
}
