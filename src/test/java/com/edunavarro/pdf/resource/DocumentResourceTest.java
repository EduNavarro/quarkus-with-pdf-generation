package com.edunavarro.pdf.resource;

import static io.restassured.RestAssured.given;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;

import com.edunavarro.pdf.event.EventData;
import com.edunavarro.pdf.service.PdfService;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;

@QuarkusTest
public class DocumentResourceTest extends DataTest {

	@Inject
	ObjectMapper objectMapper;

	@Inject
	DocumentResource documentResource;

	@InjectMock
	PdfService pdfService;

	private static final String URL = "/document/pdf";

	@Test
	public void testUploadDataWhenServiceIsOK() {

		when(pdfService.createPdfDocument(any(EventData.class))).thenReturn(true);

		given().when().header(CLOUD_EVENT_VERSION_NAME, CLOUD_EVENT_VERSION_VALUE).header(CLOUD_EVENT_ID, "MOCK-ID-01")
				.header(CLOUD_EVENT_SOURCE_NAME, CLOUD_EVENT_SOURCE_NAME)
				.header(CLOUD_EVENT_TYPE_NAME, CLOUD_EVENT_TYPE_NAME).header("Content-Type", "application/json")
				.body("{\"documentId\": \"AJ0124NIF-x00110xC\"," + "\"creationDate\": \"2020-06-15T00:00:00Z\","
						+ "\"dataList\": [{\"detailName\": \"Company\"," + "\"quantity\": 1,"
						+ "\"type\":\"GOVERMENT\"," + "\"status\": \"OPEN\"}," + "{\"detailName\": \"Person\","
						+ "\"quantity\": 23," + "\"type\": \"PERSON\"," + "\"status\": \"AUDIT\"}]}")
				.post(URL).then().statusCode(201);
	}

	@Test
	public void testUploadDataWhenServiceIsNotOK() {

		when(pdfService.createPdfDocument(any(EventData.class))).thenReturn(false);

		given().when()
			.header(CLOUD_EVENT_VERSION_NAME, CLOUD_EVENT_VERSION_VALUE)
			.header(CLOUD_EVENT_ID, "MOCK-ID-01")
			.header(CLOUD_EVENT_SOURCE_NAME, CLOUD_EVENT_SOURCE_NAME)
			.header(CLOUD_EVENT_TYPE_NAME, CLOUD_EVENT_TYPE_NAME)
			.header("Content-Type", "application/json")
				.body("{\"documentId\": \"AJ0124NIF-x00110xC\"," + "\"creationDate\": \"2020-06-15T00:00:00Z\","
						+ "\"dataList\": [{\"detailName\": \"Company\"," + "\"quantity\": 1,"
						+ "\"type\":\"GOVERMENT\"," + "\"status\": \"OPEN\"}," + "{\"detailName\": \"Person\","
						+ "\"quantity\": 23," + "\"type\": \"PERSON\"," + "\"status\": \"AUDIT\"}]}")
				.post(URL).then().statusCode(500);
	}

}