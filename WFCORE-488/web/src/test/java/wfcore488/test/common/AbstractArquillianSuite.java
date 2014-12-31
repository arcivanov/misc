/*
 * Copyright (C) 2014 Computer Science Corporation
 * All rights reserved.
 *
 */
package wfcore488.test.common;

import java.util.ArrayList;
import java.util.Arrays;

import org.jboss.arquillian.container.test.api.Testable;
import org.jboss.arquillian.protocol.servlet.arq514hack.descriptors.api.application.ApplicationDescriptor;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.spec.EnterpriseArchive;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.descriptor.api.Descriptors;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.jboss.shrinkwrap.resolver.api.maven.MavenResolvedArtifact;
import org.jboss.shrinkwrap.resolver.api.maven.PackagingType;
import org.jboss.shrinkwrap.resolver.api.maven.PomEquippedResolveStage;
import org.jboss.shrinkwrap.resolver.api.maven.ScopeType;
import org.jboss.shrinkwrap.resolver.api.maven.coordinate.MavenCoordinate;

/**
 * @author arcivanov
 */
public class AbstractArquillianSuite
{
    public static EnterpriseArchive createEjbDeployment()
    {
        return AbstractArquillianSuite.createDeployment(ShrinkWrap.create(JavaArchive.class).addPackages(true, ""));
    }

    public static EnterpriseArchive createWarDeployment()
    {
        return AbstractArquillianSuite.createDeployment(ShrinkWrap.create(WebArchive.class, "test.war").addPackages(true, ""));
    }

    public static <T extends Archive<T>> EnterpriseArchive createDeployment(T testArchive)
    {
        ApplicationDescriptor appXml = Descriptors.create(ApplicationDescriptor.class, "application.xml");
        appXml.libraryDirectory("lib");
        EnterpriseArchive ear = ShrinkWrap.create(EnterpriseArchive.class);

        PomEquippedResolveStage pomResolver = Maven.configureResolver().workOffline().loadPomFromFile("pom.xml");
        testArchive = Testable.archiveToTest(testArchive);
        if (testArchive instanceof WebArchive) {
            appXml.webModule(testArchive.getName(), "/");
        }
        else {
            appXml.ejbModule(testArchive.getName());
        }
        ear.addAsModule(testArchive);

        ArrayList<MavenResolvedArtifact> artifacts = new ArrayList<>();
        artifacts.addAll(Arrays.asList(pomResolver
                .importDependencies(ScopeType.COMPILE, ScopeType.RUNTIME, ScopeType.IMPORT, ScopeType.SYSTEM).resolve()
                .withTransitivity().asResolvedArtifact()));
        artifacts.addAll(Arrays.asList(pomResolver.importDependencies(ScopeType.TEST).resolve().withoutTransitivity()
                .asResolvedArtifact()));
        for (MavenResolvedArtifact artifact : artifacts) {
            MavenCoordinate coord = artifact.getCoordinate();
            PackagingType packaging = coord.getPackaging();
            if (PackagingType.WAR.equals(packaging)) {
                WebArchive war = ShrinkWrap.createFromZipFile(WebArchive.class, artifact.asFile());
                ear.addAsModule(war);
                appXml.webModule(war.getName(), war.getId());
            }
            else if (PackagingType.EJB.equals(packaging)) {
                JavaArchive jar = ShrinkWrap.createFromZipFile(JavaArchive.class, artifact.asFile());
                ear.addAsModule(jar);
                appXml.ejbModule(jar.getName());
            }
            else if (PackagingType.JAR.equals(packaging)) {
                JavaArchive jar = ShrinkWrap.createFromZipFile(JavaArchive.class, artifact.asFile());
                ear.addAsLibrary(jar);
            }
            else {
                throw new UnsupportedOperationException("Unknown packaging type " + packaging);
            }
        }
        String appXmlContent = appXml.exportAsString();
        ear.setApplicationXML(new StringAsset(appXmlContent));
        System.out.println("======");
        System.out.println(ear.toString(true));
        System.out.println("======");
        System.out.println(appXmlContent);
        System.out.println("======");
        System.out.println(testArchive.toString(true));
        System.out.println("======");
        return ear;
    }
}
