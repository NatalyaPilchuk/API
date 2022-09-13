package apitests.restassured;

import constants.Urls;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import java.io.File;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;

public class RestAssuredTest {
    @Test
    public void CheckUserNotFoundTest() {
        RestAssured
                .given()
                .when()
                .get(Urls.GET_USER_NOT_FOUND)
                .then()
                .statusCode(404);
    }

    @Test
    public void checkBodyValuesTest() {
        RestAssured
                .given()
                .when()
                .get(Urls.GET_LIST_RESOURCE)
                .then()
                .statusCode(200)
                .body("page", instanceOf(Integer.class))
                .body("per_page", equalTo(6));

    }

    @Test
    public void checkStaticResponseTest() {
        JsonPath expectedJson = new JsonPath(new File("src/test/resources/user.json"));
        RestAssured
                .given()
                .when()
                .get(Urls.GET_SINGLE_RESOURCE)
                .then()
                .statusCode(200)
                .body("", equalTo(expectedJson.getMap("")));

    }

    @Test
    public void getWithQueryParamTest() {
        RestAssured
                .given()
                .queryParam("page", "2")
                .when()
                .get(Urls.GET_LIST_USERS)
                .then()
                .statusCode(200)
                .body("page", equalTo(2));
    }

    @Test
    public void updateUserTest() {
        UserModel userBody = UserModel
                .builder()
                .name("Alex")
                .job("AQA")
                .build();
        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .and()
                .body(userBody)
                .when()
                .put(Urls.PUT_UPDATE)
                .then()
                .statusCode(200);

    }

    @Test
    public void patchUpdateUserTest() {
        UserModel userBody = UserModel
                .builder()
                .name("morpheus")
                .job("zion resident")
                .build();
        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .and()
                .body(userBody)
                .when()
                .patch(Urls.PATCH_UPDATE)
                .then()
                .statusCode(200);

    }

    @Test
    public void deleteUserTest() {
        RestAssured
                .given()
                .when()
                .delete(Urls.DELETE)
                .then()
                .statusCode(204);

    }

    @Test
    public void registerSuccessfulUserTest() {
        UserModel userBody = UserModel
                .builder()
                .email("eve.holt@reqres.in")
                .password("pistol")
                .build();
        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .and()
                .body(userBody)
                .when()
                .post(Urls.POST_REGISTER)
                .then()
                .statusCode(200);
    }

    @Test
    public void registerUnsuccessfulUserTest() {
        UserModel userBody = UserModel
                .builder()
                .email("sydney@fife")
                .build();
        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .and()
                .body(userBody)
                .when()
                .post(Urls.POST_REGISTER)
                .then()
                .statusCode(400);
    }

    @Test
    public void loginSuccessfulUserTest() {
        UserModel userBody = UserModel
                .builder()
                .email("eve.holt@reqres.in")
                .password("cityslicka")
                .build();
        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .and()
                .body(userBody)
                .when()
                .post(Urls.POST_LOGIN)
                .then()
                .statusCode(200);
    }

    @Test
    public void loginUnsuccessfulUserTest() {
        UserModel userBody = UserModel
                .builder()
                .email("peter@klaven")
                .build();
        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .and()
                .body(userBody)
                .when()
                .post(Urls.POST_LOGIN)
                .then()
                .statusCode(400);
    }

    @Test
    public void delayedResponseTest() {
        RestAssured
                .given()
                .when()
                .get(Urls.GET_DELAYED_RESPONSE)
                .then()
                .statusCode(200)
                .body("page", instanceOf(Integer.class))
                .body("per_page", equalTo(6));

    }

}
