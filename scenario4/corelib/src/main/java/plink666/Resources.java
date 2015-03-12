/**
 *
 */
package plink666;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.picketlink.annotations.PicketLink;

/**
 * @author jrombs
 */
public class Resources
{
    @PersistenceContext(name = "ExampleDS")
    private EntityManager em;

    @Produces
    @PicketLink
    public EntityManager getPicketLinkEntityManager()
    {
        System.out.println("somebody asked for and entity manager@@@@@@@@@@@@@@@@@@@@");
        return em;
    }
}
