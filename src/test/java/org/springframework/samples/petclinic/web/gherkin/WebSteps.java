package org.springframework.samples.petclinic.web.gherkin;


//TODO convert it to assertJ
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.hamcrest.core.StringContains.containsString;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.springframework.samples.petclinic.web.gherkin.seleniumutil.BrowserDriver;
import org.springframework.samples.petclinic.web.gherkin.seleniumutil.FormField;

import java.util.ArrayList;
import java.util.List;



public class WebSteps {
    private static final Logger LOGGER = Logger.getLogger(WebSteps.class.getName());

    /**
     * The current form involved in the steps
     */
    private WebElement currentFormElement;

    @Given("^I navigate to '(.*)'$")
    public void given_I_navigate_at(String url){
        LOGGER.info("Loading the page at " + url);
        BrowserDriver.loadPage(url);
    }

    @When("^I try to login with '(.+)' credentials$")
    public void when_I_try_to_login(String credentialsType){
        LOGGER.info("Entering: I try to login with " +
                credentialsType + " credentials");
    }

    @Then("^I should find the image '(.+)'$")
    public void then_I_login(String image){
        LOGGER.info("Looking for the image " + image);

        //The XPATH way but the error message is not useful
        //List<WebElement> webElements = BrowserDriver.getCurrentDriver().findElements(By.xpath("//img[contains(@src,'"+image+"')]"));
        //assertThat(webElements.size(),greaterThan(0));

        //hamcrest normal code
        List<WebElement> webElements = BrowserDriver.getCurrentDriver().findElements(By.tagName("img"));
        List<String> attributes = new ArrayList<String>();
        for (WebElement webElement : webElements){
           attributes.add(webElement.getAttribute("src"));
        }
        assertThat(attributes,hasItem(containsString(image)));
    }


    @When("^I fill the form '(.+)' with$")
    public void iFillTheFormWith(String formName, List<FormField> formFields) throws Throwable {
        LOGGER.info("Filling the form " + formName + " with " + formFields);
        //first check the form exist
        List<WebElement> webElements = BrowserDriver.getCurrentDriver().findElements(By.xpath("//form[@id='"+formName+"']"));
        assertThat("The form " + formName + " can not be found in this page", webElements.size(), greaterThan(0));
        if (webElements.size()>1){
            LOGGER.warn("More than one form with id " + formName + " Behaviour may be unpredictable ");
        }
        currentFormElement = webElements.get(0);
        //then for each checks it's existence and fill the form
        for (FormField formField : formFields){
            List<WebElement> inputElements = currentFormElement.findElements(By.xpath("//input[@name='"+formField.getField()+"'] | //select[@name='"+formField.getField()+"']"));
            assertThat("The element '" + formField.getField() + "' can not be found in form '" + formName +"'", inputElements.size(), greaterThan(0));
            //TODO We always suppose that there is only one possible element with this name but that's not necessary true, we're waiting for a case
            WebElement inputElement = inputElements.get(0);
            if (inputElement.getTagName()=="select"){
                Select select = new Select(inputElement);
                select.selectByVisibleText(formField.getValue());
            }else{
                inputElement.sendKeys(formField.getValue());
            }
        }

    }


    @When("^I submit$")
    public void iSubmit() throws Throwable {
        List<WebElement> submitButtonElements = currentFormElement.findElements(By.xpath("//button[@type='submit']"));
        assertThat("The form does not have a button submit", submitButtonElements.size(), greaterThan(0));
        submitButtonElements.get(0).click();
    }


    @Then("^I should find '(.*)' in the data table$")
    public void iShouldFindTextInTheDataTable(String textInTheDataTable) throws Throwable {
        LOGGER.info("Searching for " + textInTheDataTable + " in the data table");
        //first get the data table
        List<WebElement> dataTableElements = BrowserDriver.getCurrentDriver().findElements(By.cssSelector(".dataTable"));
        assertThat("Impossible to find a dataTable in the page", dataTableElements.size(), greaterThan(0));
        //if there's more than elements we should warn
        if (dataTableElements.size()>1){
            LOGGER.warn("More than one dataTable in the page, make sure that's on purpose ");
        }
        List<WebElement> elementsWithTheText = dataTableElements.get(0).findElements(By.xpath("//*[contains(text(), '"+textInTheDataTable+"')]"));
        assertThat("Impossible to find the text " + textInTheDataTable + " in the dataTable", elementsWithTheText.size(), greaterThan(0));
    }

    @Then("^I should find no data table$")
    public void iShouldFindNoDataTable() throws Throwable {
        LOGGER.info("Checking no data table are present");
        List<WebElement> dataTableElements = BrowserDriver.getCurrentDriver().findElements(By.cssSelector(".dataTable"));
        assertThat("We should no find a dataTable", dataTableElements.size(), equalTo(0));
    }

    @Then("^I should find '(.*)' in the page$")
    public void iShouldFindInthePage(String text) throws Throwable {
        LOGGER.info("Searching for " + text + " in the page");
        List<WebElement> elementsWithTheText = BrowserDriver.getCurrentDriver().findElements(By.xpath("//*[contains(text(), '"+text+"')]"));
        assertThat("Impossible to find the text " + text + " in the page", elementsWithTheText.size(), greaterThan(0));
    }

    @Then("^I should find the error message '(.*)'$")
    public void iShouldFindTheErrorMessage(String errorMessage) throws Throwable {
        LOGGER.info("Searching for the error message " + errorMessage);
        //first get the data table
        List<WebElement> errorElements = BrowserDriver.getCurrentDriver().findElements(By.xpath("//span[contains(@class,'help-inline') and contains(text(), '"+errorMessage+"')]"));
        assertThat("Impossible to find error message " + errorMessage, errorElements.size(), greaterThan(0));
    }

    @Given("^I click (.*)$")
    public void iClickThisButton(String buttonText) throws Throwable {
        LOGGER.info("Search the link btn " + buttonText);
        List<WebElement> buttonElements = BrowserDriver.getCurrentDriver().findElements(By.xpath("//a[contains(@class,'btn') and contains(text(), '"+buttonText+"')]"));
        assertThat("Impossible to find the button " + buttonText, buttonElements.size(), greaterThan(0));
        if (buttonElements.size()>1){
            LOGGER.warn("More than one button " + buttonText +" make sure that's on purpose ");
        }
        buttonElements.get(0).click();
    }
}