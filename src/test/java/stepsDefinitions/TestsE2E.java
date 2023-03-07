package stepsDefinitions;

import java.util.List;
import java.util.Map;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestsE2E {
	
	private static final String USER_ID = "9b5f49ab-eea9-45f4-9d66-bcf56a531b85";
	private static final String USERNAME = "TOOLSQA-Test";
	private static final String PASSWORD = "Test@@123";
	private static final String BASE_URL = "https://bookstore.toolsqa.com";

	private static String token;
	private static Response response;
	private static String jsonString;
	private static String bookId;
	
	@Given("I am an authorized user")
	public void i_am_an_authorized_user() {
		RestAssured.baseURI = BASE_URL;
		RequestSpecification request = RestAssured.given();

		request.header("Content-Type", "application/json");
		response = request.body("{ \"userName\":\"" + USERNAME + "\", \"password\":\"" + PASSWORD + "\"}")
				.post("/Account/v1/GenerateToken");

		String jsonString = response.asString();
		token = JsonPath.from(jsonString).get("token");
		System.out.println("Token value is: "+token);
	}
	@Given("User accesses the book services")
	public void user_accesses_the_book_services() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	@When("I fetch book list using {string} services")
	public void i_fetch_book_list_using_services(String servicename) {
	    // Write code here that turns the phrase above into concrete actions
		RestAssured.baseURI = BASE_URL;
		RequestSpecification request = RestAssured.given();
		request.header("Authorization", "Bearer " + token)
		.header("Content-Type", "application/json");
		
		if (servicename.equalsIgnoreCase("get")) {
		response = request.get("/BookStore/v1/Books");
		}
		
		jsonString = response.asString();
		List<Map<String, String>> books = JsonPath.from(jsonString).get("books");
		Assert.assertTrue(books.size() > 0);

		bookId = books.get(0).get("isbn");
		System.out.println("Book Id: "+ bookId);
	}
	@Then("I verify status code of response")
	public void i_verify_status_code() {
	    // Write code here that turns the phrase above into concrete actions
		Assert.assertEquals(200, response.getStatusCode());
		System.out.println("Response Code: "+ response.getStatusCode());
	}

}
