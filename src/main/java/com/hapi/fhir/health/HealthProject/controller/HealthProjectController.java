package com.hapi.fhir.health.HealthProject.controller;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.parser.IParser;
import com.hapi.fhir.health.HealthProject.model.AuthenticationBean;
import com.hapi.fhir.health.HealthProject.service.HealthServiceImpl;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1")
public class HealthProjectController {

    @Autowired
    HealthServiceImpl service;

    @GetMapping(path = "/basicauth")
    public AuthenticationBean basicauth() {
        return new AuthenticationBean("You are authenticated");
    }

    @GetMapping("/health")
    public String getHealthStatus(){
        return "health is good";
    }

    @GetMapping("/saveuser")
    public String saveAdminUser(){
        service.saveUser();
        return "Sucess";
    }

    @GetMapping("/patient")
    public String getPatient(){
        Patient pat=service.getPatient();
       // return pat.toString();
        FhirContext ctx = FhirContext.forR4();

        // Create a JSON parser
        IParser parser = ctx.newJsonParser();
        parser.setPrettyPrint(true);

        String encode = parser.encodeResourceToString(pat);
        return encode;
    }

    @GetMapping("/savepatient")
    public String savepatient(){
        service.savePatient();
        return "Sucess";
    }

    @GetMapping("/patientclient")
    public String getPatientFromClient(){
        Bundle bundle=service.getPatientFromClient();
        // return pat.toString();
        FhirContext ctx = FhirContext.forR4();

        // Create a JSON parser
        IParser parser = ctx.newJsonParser();
        parser.setPrettyPrint(true);

        String encode = parser.encodeResourceToString(bundle);
        return encode;
    }




}
