package com.hapi.fhir.health.HealthProject.service;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import com.hapi.fhir.health.HealthProject.model.HealthUser;
import com.hapi.fhir.health.HealthProject.repository.HealthUserReposirory;
import org.hl7.fhir.r4.model.*;
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

    public Patient getPatient(){
        Patient pat = new Patient();

        // Add a "name" element
        HumanName name = pat.addName();
        name.setFamily("Simpson").addGiven("Homer").addGiven("J");

        // Add an "identifier" element
        Identifier identifier = pat.addIdentifier();
        identifier.setSystem("http://acme.org/MRNs").setValue("7000135");

        // Model is designed to be chained
        pat.addIdentifier().setSystem("http://acme.org/MRNs").setValue("12345");

        return pat;

    }

    public void savePatient(){
        Patient pat = new Patient();
        pat.addName().setFamily("Simpson1").addGiven("Homer1").addGiven("T");
        pat.addIdentifier().setSystem("http://acme.org/MRNs").setValue("7000135");
        pat.setGender(Enumerations.AdministrativeGender.MALE);

        // Create a context
        FhirContext ctx = FhirContext.forR4();

        // Create a client
        String serverBaseUrl = "http://hapi.fhir.org/baseR4";
        IGenericClient client = ctx.newRestfulGenericClient(serverBaseUrl);

        // Use the client to store a new resource instance
        MethodOutcome outcome = client
                .create()
                .resource(pat)
                .execute();
        // Print the ID of the newly created resource
        System.out.println("if of patient"+outcome.getId());
    }

    public Bundle getPatientFromClient(){
        FhirContext ctx = FhirContext.forR4();
        IGenericClient client = ctx.newRestfulGenericClient("http://hapi.fhir.org/baseR4");

        // Build a search and execute it
        Bundle response = client.search()
                .forResource(Patient.class)
                .where(Patient.NAME.matches().value("Simpson1"))
                .count(100)
                .returnBundle(Bundle.class)
                .execute();

        // How many resources did we find?
        System.out.println("Responses: " + response.getTotal());

        // Print the ID of the first one
        System.out.println("First response ID: " + response.getEntry().get(0).getResource().getId());
        return response;
    }
}
