/**
 *
 */
package plink666;

import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import org.picketlink.idm.PartitionManager;

/**
 * @author jrombs
 */
@Singleton
@Startup
public class SecurityInitializer
{
    //@Resource(lookup = "java:/picketlink/FileCompletePartitionManager")
    @Inject
    private PartitionManager partitionManager;

    //    @PostConstruct
    //    public void create()
    //    {
    //        if (partitionManager == null) {
    //            System.out.println("Partiton Manager equal null");
    //        }
    //
    //        if (partitionManager.getPartition(Realm.class, "default") == null) {
    //            partitionManager.add(new Realm(Realm.DEFAULT_REALM));
    //            // Create user john
    //            User john = new User("john");
    //            john.setEmail("john@acme.com");
    //            john.setFirstName("John");
    //            john.setLastName("Smith");
    //
    //            IdentityManager identityManager = partitionManager.createIdentityManager();
    //            identityManager.add(john);
    //            identityManager.updateCredential(john, new Password("demo"));
    //            // Create user mary
    //            User mary = new User("mary");
    //            mary.setEmail("mary@acme.com");
    //            mary.setFirstName("Mary");
    //            mary.setLastName("Jones");
    //            identityManager.add(mary);
    //            identityManager.updateCredential(mary, new Password("demo"));
    //        }
    //    }
}
