package com.edunavarro.pdf.resource;

import static io.restassured.RestAssured.given;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class PdfGenerationIntegrationTest extends DataTest {

	private static final String URL = "/document/pdf";
	
	@Inject
	ObjectMapper objectMapper;
	
    @Test
    public void testPostEndpoint() {

        given()
          .when()
          	.header(CLOUD_EVENT_VERSION_NAME, CLOUD_EVENT_VERSION_VALUE)
          	.header(CLOUD_EVENT_ID, "MOCK-ID-01")
          	.header(CLOUD_EVENT_SOURCE_NAME, CLOUD_EVENT_SOURCE_NAME)
			.header(CLOUD_EVENT_TYPE_NAME, CLOUD_EVENT_TYPE_NAME)
			.header("Content-Type", "application/json")
		  .body("{\"documentId\": \"AJ0124NIF-x00110xC\","
		  		+ "\"creationDate\": \"2020-06-15T00:00:00Z\","
		  		+ "\"dataList\": [{\"detailName\": \"Company\","
		  		+ "\"quantity\": 1,"
		  		+ "\"type\":\"GOVERMENT\","
		  		+ "\"status\": \"OPEN\"},"
		  		+ "{\"detailName\": \"Person\","
		  		+ "\"quantity\": 23,"
		  		+ "\"type\": \"PERSON\","
		  		+ "\"status\": \"AUDIT\"}]}")
		  .post(URL)
          .then()
             .statusCode(201);
    }
    
    
    @Test
    public void testPostEndpointKO() {

        given()
          .when()
          	.header(CLOUD_EVENT_VERSION_NAME, CLOUD_EVENT_VERSION_VALUE)
          	.header(CLOUD_EVENT_ID, "MOCK-ID-01")
          	.header(CLOUD_EVENT_SOURCE_NAME, CLOUD_EVENT_SOURCE_NAME)
			.header(CLOUD_EVENT_TYPE_NAME, CLOUD_EVENT_TYPE_NAME)
			.header("Content-Type", "application/json")
		  .body("{"
		  		+ "\"creationDate\": \"2020-06-15T00:00:00Z\""
		  		+ "}")
		  .post(URL)
          .then()
             .statusCode(500);
    }

}