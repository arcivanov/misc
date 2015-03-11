/**
 *
 */
package plink666.ejb;

import javax.enterprise.event.Observes;

import org.picketlink.event.IdentityConfigurationEvent;
import org.picketlink.idm.config.IdentityConfigurationBuilder;

/**
 * @author jrombs
 */
public class IdentityManagementConfiguration
{
    /**
     * This method uses the IdentityConfigurationBuilder to create an IdentityConfiguration, which defines how PicketLink stores
     * identity-related data. In this particular example, a JPAIdentityStore is configured to allow the identity data to be stored
     * in a relational database using JPA.
     */
    public void onInit(@Observes IdentityConfigurationEvent event)
    {
        System.out.println("IDM is totally being set up!!!!!");
        IdentityConfigurationBuilder builder = event.getConfig();
        builder.named("default").stores().jpa()
        // Specify that this identity store configuration supports all features
                .supportAllFeatures();
    }
}
