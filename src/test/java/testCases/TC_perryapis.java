package testCases;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertEquals;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;


import java.io.IOException;


public class TC_perryapis extends DataForTests{
    //Declare class static variables
    private static final String baseURL = "https://perrys-summer-vacation.herokuapp.com/api";
    private int count = 0;

    @Test(dataProvider = "DataForCreateUsers", priority = 1, description = "Create User")
    public void test_createUser(String name) throws IOException {
        JSONObject request = new JSONObject();
        request.put("name",name);
        Response response = given().
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                header("Content-Type","application/json").
                body(request.toJSONString())
                .when()
                .post(baseURL + "/users")
                .then()
                .statusCode(200).extract().response();
        String jsonResponse = response.asString();
        org.json.JSONObject responseObj = new org.json.JSONObject(jsonResponse);
        super.ids[count] = responseObj.getString("id");
        count++;

    }

    @Test(dataProvider = "DataForCreateMessages", priority = 2, description = "Create Message")
    public void test_sendMessages(String from, String to, String message, int statusCode) throws IOException{
        JSONObject fromRequest = new JSONObject();
        fromRequest.put("id", from);
        JSONObject toRequest = new JSONObject();
        toRequest.put("id", to);
        JSONObject request = new JSONObject();
        request.put("from",fromRequest);
        request.put("to",toRequest);
        request.put("message",message);

        Response response= given().
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                header("Content-Type","application/json").
                body(request.toJSONString())
                .when()
                .post(baseURL + "/messages")
                .then()
                .statusCode(statusCode).extract().response();

        String jsonResponse = response.asString();
        org.json.JSONObject responseObj = new org.json.JSONObject(jsonResponse);
        String actualMessage = responseObj.getString("message");
        String id = responseObj.getString("id");
        super.messageIds.add(id);
        assertEquals (message, actualMessage);

    }

    @Test(dataProvider = "DataForListMesssages", priority = 3, description = "List Messages")
    public void test_listMessages(String from, String to, String messageId, int statusCode, int index, String message) throws IOException{

        Response response= given()
                .when()
                .get(baseURL + "/messages?from="+from + "&to="+to)
                .then()
                .statusCode(statusCode).log().body().extract().response();
        String jsonResponse = response.asString();
        JSONArray responseArray = new JSONArray(jsonResponse);
        org.json.JSONObject responseObj = responseArray.getJSONObject(index);
        String actualId = responseObj.getString("id");
        String actualMessage = responseObj.getString("message");
        assertEquals (messageId, actualId);
        assertEquals(message, actualMessage);

    }

    @Test(dataProvider = "DataForGetMesssage", priority = 4, description = "Get Message")
    public void test_getMessage(String messageId, int statusCode,  String message) throws IOException{

        Response response= given()
                .when()
                .get(baseURL + "/messages/" + messageId)
                .then()
                .statusCode(statusCode).log().body().extract().response();
        String jsonResponse = response.asString();
        org.json.JSONObject responseObj;
        if(!jsonResponse.isEmpty()) {
            responseObj = new org.json.JSONObject(jsonResponse);
            String actualId = responseObj.getString("id");
            String actualMessage = responseObj.getString("message");
            assertEquals (messageId, actualId);
            assertEquals(message, actualMessage);
        }
    }

    @Test(dataProvider = "DataForUpdateMesssage", priority = 5, description = "Update Message")
    public void test_updateMessage(String messageId, String message, int statusCode ) throws IOException{

        given()
                .when()
                .put(baseURL + "/messages/" + messageId)
                .then()
                .statusCode(statusCode);
        if(statusCode==200) {
            Response response= given()
                    .when()
                    .get(baseURL + "/messages/" + messageId)
                    .then()
                    .statusCode(200).log().body().extract().response();
            String jsonResponse = response.asString();
            org.json.JSONObject responseObj;
            if(!jsonResponse.isEmpty()) {
                responseObj = new org.json.JSONObject(jsonResponse);
                String actualMessage = responseObj.getString("message");
                assertEquals(message, actualMessage);
            }
        }
    }



    @Test(dataProvider = "DataForDeleteMesssage", priority = 6, description = "Delete Message")
    public void test_deleteMessage(String messageId, String from, String to, int statusCode) throws IOException{

        given()
                .when()
                .delete(baseURL + "/messages/" + messageId)
                .then()
                .statusCode(statusCode);
            if(statusCode==204) {
                Response response= given()
                        .when()
                        .get(baseURL + "/messages?from="+from + "&to="+to)
                        .then()
                        .statusCode(200).log().body().extract().response();
                String jsonResponse = response.asString();
                boolean actualResponse = jsonResponse.contains(messageId);
                assertEquals (false, actualResponse);

            }

    }


}
