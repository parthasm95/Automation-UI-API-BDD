package stepdefinitions.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class ApiStepDefinition {
	
	private RequestSpecification request;
	private Response response;
	
	@Given("I have the url to get all bookings")
	public void i_have_the_url_to_get_all_bookings() {
	    RestAssured.baseURI = "https://restful-booker.herokuapp.com";
	    request = RestAssured.given();
	}

	@When("I send get request")
	public void i_send_get_request() {
		response = request.get("/booking");
		System.out.println(response.asPrettyString());
	}

	@Then("status code should be {int}")
	public void status_code_should_be(Integer int1) {
		System.out.println("Status Code : "+ response.getStatusCode());
	    assertEquals(200, response.statusCode());
	}

	@Then("all booking IDs should be present in response")
	public void all_booking_i_ds_should_be_present_in_response() throws JsonMappingException, JsonProcessingException {
	    ObjectMapper objectMapper = new ObjectMapper();
	    JsonNode jsonResponse = objectMapper.readTree(response.asString());
	    System.out.println("Totel number of Bookings Present : "+jsonResponse.size());
	    assertTrue("Expected booking Ids gether than 1", jsonResponse.size()>1);
	}
	
	@Given("I have the url to create new booking")
	public void i_have_the_url_to_create_new_booking() {
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
	    request = RestAssured.given();
	}

	@When("I send post request with payload")
	public void i_send_post_request_with_payload() throws StreamReadException, DatabindException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		Object payLoad = objectMapper.readValue(new File("src\\test\\resources\\datadriven\\payload\\createBookingPayload.json"), Object.class);
		//Alternative Method
		//String payLoad = new String(Files.readAllBytes(Paths.get("src\\test\\resources\\datadriven\\payload\\createBookingPayload.json")));
		request.body(payLoad);
		request.contentType(ContentType.JSON);
		response = request.post("/booking");
		System.out.println(response.asPrettyString());
	}

	@Then("the response body should contain booking id and requested payload")
	public void the_response_body_should_contain_booking_id_and_requested_payload() {
		String bookingId = response.jsonPath().getString("bookingid");
		System.out.println("Booking Id is : "+ bookingId);
	    assertNotNull("Booking id should not null", bookingId);
	}
	
	@Then("the response body should be as per schema {string}")
	public void the_response_body_should_be_as_per_schema(String fileName) throws IOException, ProcessingException {
	    File schema = new File("src\\test\\resources\\datadriven\\JsonSchema\\" + fileName);
	    ObjectMapper mapper = new ObjectMapper();
        JsonNode schemaNode = mapper.readTree(schema);
        JsonSchemaFactory factory = JsonSchemaFactory.byDefault();
        JsonSchema schemaValidator = factory.getJsonSchema(schemaNode);
        JsonNode responseNode = mapper.readTree(response.asString());
        schemaValidator.validate(responseNode);
	}
	

}
