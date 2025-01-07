package stepDefinations;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import java.io.IOException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resoruces.APIResources;
import resoruces.TestDataBuild;
import resoruces.Utils;

public class StepDefinations extends Utils{
	TestDataBuild data = new TestDataBuild() ;
	RequestSpecification res;
	ResponseSpecification resb;
	Response response;
	static String placeId;
	
	@Given("Add Place Payload {string} {string} {string}")
	public void add_place_payload(String name, String language, String address) throws IOException {
		res = given().spec(requestSpecifications()).body(data.addPlacePayload(name, language, address)).log().all();
	}
	@When("User calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String method) {
		APIResources resourceAPI = APIResources.valueOf(resource);
		System.out.println(resourceAPI.getResource());
		if(method.equalsIgnoreCase("POST")) {
			response = res.when().post(resourceAPI.getResource());
		}else if(method.equalsIgnoreCase("GET")){
			response = res.when().get(resourceAPI.getResource());
	  }
	}
	@Then("the API call got success with status code {int}")
	public void the_api_call_got_success_with_status_code(Integer int1) {
	    assertEquals(response.getStatusCode(),200);
	}
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String expectedValue) {
		resb = new ResponseSpecBuilder().expectContentType(ContentType.JSON).build();
		response.then().spec(resb).log().all().extract().response();
		assertEquals(getJsonPath(response,keyValue), expectedValue);		
	}
	@Then("verify place_Id created is mapped to {string} using {string}")
	public void verify_place_id_created_is_mapped_to_using(String expectedname, String resource) throws IOException {
		placeId = getJsonPath(response,"place_id");
		res = given().spec(requestSpecifications()).queryParam("place_id", placeId);
		user_calls_with_http_request(resource, "GET");
		response.then().log().all();
		String actualname = getJsonPath(response,"name");
		assertEquals(actualname,expectedname);
	}
	@Given("DeletePlacePayload")
	public void delete_place_payload() throws IOException {
	   res = given().spec(requestSpecifications()).body(data.deletePlacePyload(placeId)).log().all();
	}
}
