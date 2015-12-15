package org.springframework.samples.petclinic.service.gherkin;

import cucumber.api.Format;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.PetType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by michael on 15/12/15.
 */
public class RefundSteps {


    Date currentDate;
    Date visitDate;
    List<Owner> owners;
    Owner owner;
    List<Pet> pets;

    @Given("the current date is '(.*)'")
    public void setCurrentDate(@Format("yyyy-MM-dd") Date date){
        this.currentDate = date;
    }

    /**
     * Intialise a owner
     * @param _owners
     * @throws Throwable
     */
    @Given("^we have in the customers the owner$")
    public void weHaveInTheCustomersTheOwner(List<OwnerBean> _owners) throws Throwable {
        owner = makeOwnerFromDataset(_owners).get(0);
    }

    /**
     * Dataset initialisation method
     * @param _owners
     * @throws Throwable
     */
    List<Owner> makeOwnerFromDataset(List<OwnerBean> _owners){
        List<Owner> owners = new ArrayList<Owner>();
        for(OwnerBean ownerBean : _owners){
            Owner owner = new Owner();
            owner.setFirstName(ownerBean.firstName);
            owner.setLastName(ownerBean.lastName);
            owner.setAddress(ownerBean.address);
            owner.setCity(ownerBean.city);
            owners.add(owner);
        }
        return owners;
    }

    /**
     * Dataset initialisation method
     * @param _pets
     * @throws Throwable
     */
    @Given("^this owner own the pet$")
    public void thisOwnerOwnThePet(List<PetBean> _pets) throws Throwable {
        owner.addPet( makePetFromDataset(_pets).get(0));
    }

    List<Pet> makePetFromDataset(List<PetBean> _pets){
        List<Pet> pets = new ArrayList<Pet>();
        for(PetBean petBean : _pets){
            Pet pet = new Pet();
            pet.setName(petBean.name);
            PetType petType = new PetType();
            petType.setName(petBean.type);
            pet.setType(petType);
            pets.add(pet);
        }
        return pets;
    }

    @When("^the visit occur the '(.*)' and it cost (\\d+) \\$$")
    public void theVisitOccurTheAndItCost$(@Format("yyyy-MM-dd") Date visitDate, float price) throws Throwable {
        this.visitDate = visitDate;
    }

    @Then("^the refund should be (\\d+) \\$$")
    public void theRefundShouldBe$(float price) throws Throwable {

    }
}
