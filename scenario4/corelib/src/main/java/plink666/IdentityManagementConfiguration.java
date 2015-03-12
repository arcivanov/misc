/**
 *
 */
package plink666;

import javax.enterprise.event.Observes;

import org.picketlink.event.IdentityConfigurationEvent;
import org.picketlink.idm.config.IdentityConfigurationBuilder;

/**
 * @author jrombs
 */
public class IdentityManagementConfiguration
{
    public void onInit(@Observes IdentityConfigurationEvent event)
    {
        System.out.println("IDM is totally being set up!!!!!");
        IdentityConfigurationBuilder builder = event.getConfig();
        builder.named("default").stores().jpa()
        // Specify that this identity store configuration supports all features
        .supportAllFeatures();
    }
}
