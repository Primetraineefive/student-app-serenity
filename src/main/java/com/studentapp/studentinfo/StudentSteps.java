package com.studentapp.studentinfo;

import com.studentapp.constants.EndPoints;
import com.studentapp.model.StudentPojo;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Jay
 */
public class StudentSteps {

    @Step("Creating student with firstName: {0}, lastName: {1}, email: {2}, programme: {3} and courses: {4}")
    public ValidatableResponse createStudent(String firstName, String lastName, String email,
                                             String programme, List<String> courses) {
        StudentPojo studentPojo = StudentPojo.getStudentPojo(firstName, lastName, email, programme, courses);
        return SerenityRest.rest().given().log().all()
                .header("Content-Type", "application/json")
                .body(studentPojo)
                .when()
                .post()
                .then();
    }

    @Step("Getting the student information with firstName: {0}")
    public HashMap<String, Object> getStudentInfoByFirstname(String firstName) {
        String p1 = "findAll{it.firstName=='";
        String p2 = "'}.get(0)";
        return SerenityRest.rest()
                .given().log().all()
                .when()
                .get(EndPoints.GET_ALL_STUDENT)
                .then()
                .statusCode(200)
                .extract()
                .path(p1 + firstName + p2);
    }

    @Step("Updating student information with studentId: {0}, firstName: {1}, lastName: {2}, email: {3}, programme: {4} and courses: {5}")
    public ValidatableResponse updateStudent(int studentId, String firstName, String lastName, String email,
                                             String programme, List<String> courses) {
        StudentPojo studentPojo = StudentPojo.getStudentPojo(firstName, lastName, email, programme, courses);

        return SerenityRest.rest().given().log().all()
                .header("Content-Type", "application/json")
                .pathParam("studentID", studentId)
                .body(studentPojo)
                .when()
                .put(EndPoints.UPDATE_STUDENT_BY_ID)
                .then();
    }

    @Step("Deleting student information with studentId: {0}")
    public ValidatableResponse deleteStudent(int studentId) {
        return SerenityRest.rest()
                .given()
                .pathParam("studentID", studentId)
                .when()
                .delete(EndPoints.DELETE_STUDENT_BY_ID)
                .then();
    }

    @Step("Getting student information with studentId: {0}")
    public ValidatableResponse getStudentById(int studentId) {
        return SerenityRest.rest()
                .given()
                .pathParam("studentID", studentId)
                .when()
                .get(EndPoints.GET_SINGLE_STUDENT_BY_ID)
                .then();
    }

    @Step("Getting the student information with email: {0}")
    public HashMap<String, Object> getStudentInfoByEmail(String email) {
        String p1 = "findAll{it.email=='";
        String p2 = "'}.get(0)";
        return SerenityRest.rest()
                .given().log().all()
                .when()
                .get(EndPoints.GET_ALL_STUDENT)
                .then()
                .statusCode(200)
                .extract()
                .path(p1 + email + p2);
    }

    @Step("Getting all students information")
    public ValidatableResponse getAllStudentsInfo(){
        return SerenityRest.rest()
                .given()
                .when()
                .get(EndPoints.GET_ALL_STUDENT)
                .then();
    }

}
