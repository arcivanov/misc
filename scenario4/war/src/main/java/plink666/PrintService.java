/**
 *
 */
package plink666;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author jrombs
 */
@Path("/print")
public class PrintService
{
    @GET
    @Path("/hello")
    @Produces(MediaType.APPLICATION_JSON)
    public String getSillyStuff()
    {
        return "Hello.";
    }
}
