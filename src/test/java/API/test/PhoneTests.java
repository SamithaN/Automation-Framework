package API.test;

import API.endpoints.PhoneEndPoints;
import API.payload.Data;
import API.payload.Phone;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PhoneTests {
    Phone phonePayload;
    Data data;
    private String newPhoneId;


    @BeforeClass
    public void setupData()
    {

        phonePayload = new Phone();
        data = new Data();
        data.setCapacity("128GB");
        data.setColor("White");
        data.setYear(1999);
        data.setPrise(123);

        phonePayload.setId(12);
        phonePayload.setName("");
        phonePayload.setData(data);
    }

    @Test(priority = 1)
    public void testPost() {
        Response response = PhoneEndPoints.createPhone("Apple MacBook Pro 16", 2019, 1849.99, "Intel Core i9", "1 TB");
        response.then().log().all();

        newPhoneId = response.jsonPath().getString("id");

        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 2)
    public void testGet() {
        Response response = PhoneEndPoints.getPhone();
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 3, dependsOnMethods = {"testPost"})
    public void testUpdate() {
        Response response = PhoneEndPoints.updatePhone(phonePayload,newPhoneId);
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200);

        // Parse the response JSON
        JsonPath jsonResponse = response.jsonPath();

        Assert.assertEquals(jsonResponse.getInt("data.year"), 1999);
        Assert.assertEquals(jsonResponse.getString("data.color"), "White");
    }

    @Test(priority = 4, dependsOnMethods = {"testPost"})
    public void testDelete() {
        Response response = PhoneEndPoints.deletePhone(newPhoneId);
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200);
    }

}
