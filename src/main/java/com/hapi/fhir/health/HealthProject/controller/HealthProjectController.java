package com.hapi.fhir.health.HealthProject.controller;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.parser.IParser;
import org.hl7.fhir.dstu3.model.HumanName;
import org.hl7.fhir.dstu3.model.Identifier;
import org.hl7.fhir.dstu3.model.Patient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/fhir")
public class HealthProjectController {

    private FhirContext ctx = null;

    public FhirContext getCtx() {
        return ctx;
    }

    public void setCtx(FhirContext ctx) {
        this.ctx = ctx;
    }

    public HealthProjectController()
    {
        // Do it once because expensive!
        ctx = FhirContext.forDstu3();
        System.out.println("Initialized new FhirContext()");

    }

    @GetMapping("/health")
    public String getHealthStatus(){
        return "health is good";
    }

   @GetMapping("/Patient")
    public String getPatientDetails(HttpServletRequest request, HttpServletResponse response)
    {
        Patient patient=new Patient();

        HumanName humanName = patient.addName();
        humanName.setFamily("Simpson").addGiven("Homer").addGiven("J");
        Identifier identifier=patient.addIdentifier();
        identifier.setSystem("http://acme.org/MRNs").setValue("7000135");
        patient.addIdentifier().setSystem("Library card").setValue("12345");
        IParser p = FhirContext.forDstu3().newJsonParser().setPrettyPrint(true);
        String encoded = p.encodeResourceToString(patient);

        return encoded;
    }

}
