import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class APIRequests {

    public String boardID="";
    public ArrayList cardIDList = new ArrayList();

    public void createNewBoard(String boardName){
        //Specify the base URI
        RestAssured.baseURI = "https://api.trello.com/1";
        //Request object
        RequestSpecification httpRequest = RestAssured.given();

        JSONObject requestParams = new JSONObject();
        requestParams.put("key", "5a9a987a2a9b5a0f03f0e6f04c0247c7");
        requestParams.put("token","b5b8e7487fce6ed46e29e0ab9e93ac8004539c4295b98b81a18133f25268787a");
        //requestParams.put("Cookie","dsc=21a32fc9920012a2b64c647c76e491fe257208dfa249361a95c600522c9a0845");
        requestParams.put("name", boardName);

        httpRequest.header("Content-type", "application/json");
        httpRequest.body(requestParams.toString());

        //Response object
        Response response = httpRequest.request(Method.POST,"/boards");

        this.boardID = response.jsonPath().getString("id");

        int statusCode = response.getStatusCode();
        System.out.println(statusCode);
        Assert.assertEquals(statusCode,200);

    }

    public void createNewCard(String cardName){
        String idList = increment(this.boardID);

        //Specify the base URI
        RestAssured.baseURI = "https://api.trello.com/1";
        //Request object
        RequestSpecification httpRequest = RestAssured.given();

        JSONObject requestParams = new JSONObject();
        requestParams.put("key", "5a9a987a2a9b5a0f03f0e6f04c0247c7");
        requestParams.put("token","b5b8e7487fce6ed46e29e0ab9e93ac8004539c4295b98b81a18133f25268787a");
        requestParams.put("idList", idList);
        requestParams.put("name", cardName);

        httpRequest.header("Content-type", "application/json");
        httpRequest.body(requestParams.toString());

        //Response object
        Response response = httpRequest.request(Method.POST,"/cards");

        int statusCode = response.getStatusCode();
        System.out.println(statusCode);
        Assert.assertEquals(statusCode,200);

        this.cardIDList.add(response.jsonPath().getString("id"));
    }

    public String increment(String number) {
        char[] cars = number.toCharArray();
        for (int i = cars.length - 1; i >= 0; i--) {
            if (cars[i] == 'z') {
                cars[i] = 'a';
            } else if (cars[i] == '9') {
                cars[i] = '0';
            } else {
                cars[i]++;
                break;
            }
        }
        return String.valueOf(cars);
    }

    public void updateCard(String name){

        String cardID = (String) this.cardIDList.get(0);
        //Specify the base URI
        RestAssured.baseURI = "https://api.trello.com/1";
        //Request object
        RequestSpecification httpRequest = RestAssured.given();

        JSONObject requestParams = new JSONObject();
        requestParams.put("key", "5a9a987a2a9b5a0f03f0e6f04c0247c7");
        requestParams.put("token","b5b8e7487fce6ed46e29e0ab9e93ac8004539c4295b98b81a18133f25268787a");
        requestParams.put("name", name);

        httpRequest.header("Content-type", "application/json");
        httpRequest.body(requestParams.toString());

        //Response object
        Response response = httpRequest.request(Method.PUT,"/cards/" + cardID);

        int statusCode = response.getStatusCode();
        String updatedName = response.jsonPath().getString("name");

        System.out.println(statusCode);
        Assert.assertEquals(statusCode,200);
        Assert.assertEquals(updatedName,name);
    }

    public void deleteCard1(){
        String cardID = (String) this.cardIDList.get(0);
        //Specify the base URI
        RestAssured.baseURI = "https://api.trello.com/1";
        //Request object
        RequestSpecification httpRequest = RestAssured.given();

        JSONObject requestParams = new JSONObject();
        requestParams.put("key", "5a9a987a2a9b5a0f03f0e6f04c0247c7");
        requestParams.put("token","b5b8e7487fce6ed46e29e0ab9e93ac8004539c4295b98b81a18133f25268787a");

        httpRequest.header("Content-type", "application/json");
        httpRequest.body(requestParams.toString());

        //Response object
        Response response = httpRequest.request(Method.DELETE,"/cards/" + cardID);

        int statusCode = response.getStatusCode();

        System.out.println(statusCode);
        Assert.assertEquals(statusCode,200);
    }

    public void deleteCard2(){
        String cardID = (String) this.cardIDList.get(1);
        //Specify the base URI
        RestAssured.baseURI = "https://api.trello.com/1";
        //Request object
        RequestSpecification httpRequest = RestAssured.given();

        JSONObject requestParams = new JSONObject();
        requestParams.put("key", "5a9a987a2a9b5a0f03f0e6f04c0247c7");
        requestParams.put("token","b5b8e7487fce6ed46e29e0ab9e93ac8004539c4295b98b81a18133f25268787a");

        httpRequest.header("Content-type", "application/json");
        httpRequest.body(requestParams.toString());

        //Response object
        Response response = httpRequest.request(Method.DELETE,"/cards/" + cardID);

        int statusCode = response.getStatusCode();

        System.out.println(statusCode);
        Assert.assertEquals(statusCode,200);
    }

    public void deleteBoard(){
        //Specify the base URI
        RestAssured.baseURI = "https://api.trello.com/1";
        //Request object
        RequestSpecification httpRequest = RestAssured.given();

        JSONObject requestParams = new JSONObject();
        requestParams.put("key", "5a9a987a2a9b5a0f03f0e6f04c0247c7");
        requestParams.put("token","b5b8e7487fce6ed46e29e0ab9e93ac8004539c4295b98b81a18133f25268787a");

        httpRequest.header("Content-type", "application/json");
        httpRequest.body(requestParams.toString());

        //Response object
        Response response = httpRequest.request(Method.DELETE,"/boards/" + boardID);

        int statusCode = response.getStatusCode();

        System.out.println(statusCode);
        Assert.assertEquals(statusCode,200);
    }

}

