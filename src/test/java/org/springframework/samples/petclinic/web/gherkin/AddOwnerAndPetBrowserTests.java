package org.springframework.samples.petclinic.web.gherkin;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "classpath:scenario/petclinic/webapp/petclinic_basic.feature"
)
public class AddOwnerAndPetBrowserTests {

}
