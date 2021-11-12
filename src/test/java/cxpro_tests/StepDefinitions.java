package cxpro_tests;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class StepDefinitions {
    @Given("I can load {string}")
    public void i_can_load(String string) {
        // Write code here that turns the phrase above into concrete actions
        Selen.driver.get("https://www.youtube.com/");
        System.out.println("I can load " + string);
    }
    @Then("I can log in as Loan Officer")
    public void i_can_log_in_as_loan_officer() {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("I can log in as Loan Officer");
    }

    @Then("I can see my applicants")
    public void i_can_see_my_applicants() {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("I can see my applicants");
    }

    @Then("I can create a new application")
    public void i_can_create_a_new_application() {
        // Write code here that turns the phrase above into concrete actions
        int i = 1 / 0;
    }
}
