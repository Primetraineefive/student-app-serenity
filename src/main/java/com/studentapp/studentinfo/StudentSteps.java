package com.studentapp.studentinfo;

import com.studentapp.model.StudentPojo;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.List;

/**
 * Created by Jay
 */
public class StudentSteps {

    @Step("Creating student with firstName: {0}, lastName: {1}, email: {2}, programme: {3} and courses: {4}")
    public ValidatableResponse createStudent(String firstName, String lastName, String email,
                                             String programme, List<String> courses){

        StudentPojo studentPojo = new StudentPojo();
        studentPojo.setFirstName(firstName);
        studentPojo.setLastName(lastName);
        studentPojo.setEmail(email);
        studentPojo.setProgramme(programme);
        studentPojo.setCourses(courses);

        return SerenityRest.rest().given().log().all()
                .header("Content-Type", "application/json")
                .body(studentPojo)
                .when()
                .post()
                .then();
    }

}
