package org.springframework.samples.petclinic.service.gherkin;

/**
 * Created by michael on 15/12/15.
 * Just to push a dataTable of owners
 */
public class OwnerBean {

    String firstName;
    String lastName;
    String address;
    String city;
    String telephone;

    public OwnerBean(String firstName, String lastName, String address, String city, String telephone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.telephone = telephone;
    }
}
