package com.edunavarro.pdf.resource;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import com.edunavarro.pdf.event.EventData;
import com.edunavarro.pdf.service.PdfService;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.cloudevents.CloudEvent;
import io.cloudevents.jackson.PojoCloudEventDataMapper;
import io.quarkus.logging.Log;

@ApplicationScoped
@Path("/document")
public class DocumentResource {

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
    @Consumes(MediaType.APPLICATION_JSON)
    public Response uploadData(final CloudEvent event, @Context UriInfo uriInfo) {
        EventData payload = PojoCloudEventDataMapper
                .from(mapper, EventData.class)
                .map(event.getData())
                .getValue();

        Log.infof("Processing event into pdf document with cloudEvent id %s", event.getId());
        Response response = null;
        
        
        if (pdfService.createPdfDocument(payload)) {
            UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();

        	response = Response.created(uriBuilder.build()).build();
        } else {
        	response = Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }
        
        return response;
    }
}
