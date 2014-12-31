/*
 * Copyright (C) 2014 Computer Science Corporation
 * All rights reserved.
 *
 */
package wfcore488.test;

import javax.inject.Inject;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import wfcore488.test.common.ServiceTestBase;
import wfcore488.web.ExampleService;

/**
 * @author arcivanov
 */
@RunWith(Arquillian.class)
public class ServiceIT extends ServiceTestBase
{
    @Inject
    private ExampleService service;

    @Test
    public void testService()
    {
        String result = service.getTestMethodResult();
        System.out.println(result);
        Assert.assertNotNull(result);
    }
}
