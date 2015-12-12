package org.springframework.samples.petclinic.model.gherkin;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * Created by michael on 12/12/15.
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        features = "classpath:scenario/petclinic/model/Person.feature"
)
public class PersonValidatorTest {}
