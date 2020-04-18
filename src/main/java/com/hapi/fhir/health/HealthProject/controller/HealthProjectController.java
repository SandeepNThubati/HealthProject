package com.hapi.fhir.health.HealthProject.controller;

import com.hapi.fhir.health.HealthProject.service.HealthService;
import com.hapi.fhir.health.HealthProject.service.HealthServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/fhir")
public class HealthProjectController {

    @Autowired
    HealthServiceImpl service;

    @GetMapping("/health")
    public String getHealthStatus(){
        return "health is good";
    }

    @GetMapping("/saveuser")
    public String saveAdminUser(){
        service.saveUser();
        return "Sucess";
    }



}
