package API.test;

import API.endpoints.PhoneEndPoints;
import API.payload.Data;
import API.payload.Phone;
import API.utilities.DataProviders;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DDTests {
    @Test(priority = 1, dataProvider = "Data", dataProviderClass = DataProviders.class)
    public void testPostPhone(String name, String year, String price, String CPUmodel, String harddiskSize)
    {
        Response response = PhoneEndPoints.createPhone(name,Integer.parseInt(year),Double.parseDouble(price),CPUmodel,harddiskSize);
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(),200);
    }
}
