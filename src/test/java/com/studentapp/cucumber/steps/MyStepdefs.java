package com.studentapp.cucumber.steps;

import com.studentapp.studentinfo.StudentSteps;
import com.studentapp.utils.TestUtils;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasValue;

/**
 * Created by Jay
 */
public class MyStepdefs {

    static String email = null;
    static ValidatableResponse response = null;

    @Steps
    StudentSteps studentSteps;

    @When("^User sends a GET request to list endpoint, they must get back a valid status code 200$")
    public void userSendsAGETRequestToListEndpointTheyMustGetBackAValidStatusCode() {
        studentSteps.getAllStudentsInfo().statusCode(200);
    }

    @When("^I create a new student by providing the information firstName \"([^\"]*)\" lastName \"([^\"]*)\" email \"([^\"]*)\" programme \"([^\"]*)\" courses \"([^\"]*)\"$")
    public void iCreateANewStudentByProvidingTheInformationFirstNameLastNameEmailProgrammeCourses(String firstName, String lastName, String _email, String programme, String courses) {
        List<String> courseList = new ArrayList<>();
        courseList.add(courses);
        email = TestUtils.getRandomValue()+ _email;
        response = studentSteps.createStudent(firstName, lastName, email, programme, courseList);
    }

    @Then("^I verify that the student with \"([^\"]*)\" is created$")
    public void iVerifyThatTheStudentWithIsCreated(String _email) {
        response.statusCode(201);
        HashMap<String, Object> studentInfo = studentSteps.getStudentInfoByEmail(email);
        assertThat(studentInfo, hasValue(email));

    }
}
