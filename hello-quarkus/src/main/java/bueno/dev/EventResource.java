package bueno.dev;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/send")
public class EventResource {
    public static final Logger logger = LoggerFactory.getLogger(EventResource.class);
    @Inject
    EventProducer eventProducer;

    @GET
    @Path("/{event}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response commandExecutor(@javax.ws.rs.PathParam("event") String event) {
        logger.info("Command received: " + event + " to execute");
        eventProducer.toExecute(event);
        return Response.ok().build();
    }

}