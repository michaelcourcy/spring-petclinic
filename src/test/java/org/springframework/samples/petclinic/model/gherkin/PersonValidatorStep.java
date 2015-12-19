package org.springframework.samples.petclinic.model.gherkin;

/**
 * Created by michael on 12/12/15.
 */

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.fr.Etantdonné;
import cucumber.api.java.en.Then;
import cucumber.api.java.fr.Alors;
import cucumber.api.java.en.When;
import cucumber.api.java.fr.Quand;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.samples.petclinic.model.Person;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Locale;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class PersonValidatorStep {

    public static final String ENGLISH = "english";
    public static final String FRENCH = "français";

    /**
     * The person we test
     */
    private Person personUnderTest;

    /**
     * the constraint violation we get after trying to validate
     */
    private Set<ConstraintViolation<Person>> constraintViolations;

    @Etantdonné("la langue est '(.*)'")
    @Given("the language is '(.*)'")
    public void setLocale(String language){
        if (language.equals(ENGLISH)){
            LocaleContextHolder.setLocale(Locale.ENGLISH);
        }else if (language.equals(FRENCH)){
            LocaleContextHolder.setLocale(Locale.FRENCH);
        }else{
            throw new RuntimeException("Language " + language + " not supported");
        }
    }

    @Etantdonné("^on crée une nouvelle personne$")
    @Given("^we create a new person$")
    public void createPerson(){
        personUnderTest = new Person();
    }


    @Etantdonné("^on valorise le prénom à '(.*)'$")
    @Given("^we set firstName '(.*)'$")
    public void weSetFirstName(String firstName) throws Throwable {
        personUnderTest.setFirstName(firstName);
    }

    @Etantdonné("^on valorise le nom à '(.*)'$")
    @Given("^we set lastName '(.*)'$")
    public void weSetLastName(String lastName) throws Throwable {
        personUnderTest.setLastName(lastName);
    }


    @Quand("^on applique la validation$")
    @When("^we apply validation$")
    public void weApplyValidation() throws Throwable {
        Validator validator = createValidator();
        constraintViolations = validator.validate(personUnderTest);
    }


    private Validator createValidator() {
        LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
        localValidatorFactoryBean.afterPropertiesSet();
        return localValidatorFactoryBean;
    }

    @Alors("^on devrait avoir le message d'erreur sur '(.*)' qui dit '(.*)'$")
    @Then("^we should have an error message on '(.*)' that says '(.*)'$")
    public void weShouldHaveAnErrorMessage(String propertyWithMessage, String message){
        ConstraintViolation<Person> violation = constraintViolations.iterator().next();
        assertThat(violation.getPropertyPath().toString()).isEqualTo(propertyWithMessage);
        assertThat(violation.getMessage()).isEqualTo(message);
    }

    @Alors("^on devrait avoir (\\d+) violation$")
    @Then("^we should have (\\d+) violation$")
    public void weShouldHaveAErrorMessageOnFirstNameThatSaysMayNotBeEmpty(Integer numberOfViolations) throws Throwable {
        assertThat(constraintViolations.size()).isEqualTo(numberOfViolations);
    }
}


