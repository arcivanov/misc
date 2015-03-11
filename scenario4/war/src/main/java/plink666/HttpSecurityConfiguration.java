/**
 *
 */
package plink666;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.Default;

import org.picketlink.config.SecurityConfigurationBuilder;
import org.picketlink.event.SecurityConfigurationEvent;

/**
 * @author jrombs
 */
@Default
public class HttpSecurityConfiguration
{
    public void onInit(@Observes SecurityConfigurationEvent event)
    {
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" + "I've been called. this is HttpSecurityConfiguration btw.");
        SecurityConfigurationBuilder builder = event.getBuilder();
        builder.http().allPaths().authenticateWith().basic().realmName("default");
    }

}
