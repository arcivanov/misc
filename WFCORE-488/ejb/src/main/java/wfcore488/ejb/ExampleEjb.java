/*
 * Copyright (C) 2014 Computer Science Corporation
 * All rights reserved.
 *
 */
package wfcore488.ejb;

import javax.ejb.Stateless;
import javax.inject.Inject;

import wfcore488.lib.DateGenerator;

/**
 * @author arcivanov
 */
@Stateless
public class ExampleEjb
{
    @Inject
    private DateGenerator dateGen;

    public String testMethod()
    {
        return dateGen.getDate();
    }
}
