import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.Test;

public class TestTrello {
    APIRequests apiRequests = new APIRequests();

    @Test
    public void Test_01(){
        apiRequests.createNewBoard("MyBoard");
        apiRequests.createNewCard("MyCard1");
        apiRequests.createNewCard("MyCard2");
        apiRequests.updateCard("MyCardNameUpdated");
        apiRequests.deleteCard1();
        apiRequests.deleteCard2();
        apiRequests.deleteBoard();
    }
}
