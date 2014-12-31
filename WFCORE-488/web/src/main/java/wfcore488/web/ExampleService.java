/*
 * Copyright (C) 2014 Computer Science Corporation
 * All rights reserved.
 *
 */
package wfcore488.web;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import wfcore488.ejb.ExampleEjb;

/**
 * @author arcivanov
 */
@Path("/example")
public class ExampleService
{
    @EJB
    private ExampleEjb exampleEjb;

    @GET
    @Path("/date")
    @Produces(MediaType.APPLICATION_JSON)
    public String getTestMethodResult()
    {
        return exampleEjb.testMethod();
    }
}
