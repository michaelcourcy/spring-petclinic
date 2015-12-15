package org.springframework.samples.petclinic.service.gherkin;

import cucumber.api.Format;

import java.util.Date;

/**
 * Created by michael on 15/12/15.
 */
public class PetBean {

    String name;
    Date birthDate;
    String type;


    public PetBean(String name, @Format("yyyy-MM-dd")Date birthDate , String type) {
        this.name = name;
        this.birthDate = birthDate;
        this.type = type;
    }
}
