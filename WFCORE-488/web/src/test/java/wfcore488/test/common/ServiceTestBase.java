/*
 * Copyright (C) 2014 Computer Science Corporation
 * All rights reserved.
 *
 */
package wfcore488.test.common;

import org.eu.ingwar.tools.arquillian.extension.suite.annotations.ArquillianSuiteDeployment;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.TargetsContainer;
import org.jboss.shrinkwrap.api.spec.EnterpriseArchive;

/**
 * @author jrombs
 */
@ArquillianSuiteDeployment
public class ServiceTestBase extends AbstractArquillianSuite
{
    @Deployment
    @TargetsContainer("primary-clustered")
    public static EnterpriseArchive createDeployment()
    {
        return AbstractArquillianSuite.createWarDeployment();
    }
}
