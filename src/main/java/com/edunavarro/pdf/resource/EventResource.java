package com.edunavarro.pdf.resource;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.edunavarro.pdf.event.EventData;
import com.edunavarro.pdf.service.PdfService;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.cloudevents.CloudEvent;
import io.cloudevents.jackson.PojoCloudEventDataMapper;
import io.quarkus.logging.Log;

@ApplicationScoped
@Path("/document")
public class EventResource {

    @Inject
    ObjectMapper mapper;
	
    @Inject
    PdfService pdfService;
	
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from RESTEasy Reactive, Edu";
    }
    
    @POST
    @Path("/pdf")
    public Response uploadData(final CloudEvent event) {
        EventData payload = PojoCloudEventDataMapper
                .from(mapper, EventData.class)
                .map(event.getData())
                .getValue();

        Log.infof("Processing event into pdf document with cloudEvent id %s", event.getId());
        
        pdfService.createPdfDocument(payload);
        
        return null;
    }
}
