//package bueno.dev;
//
//import org.eclipse.microprofile.reactive.messaging.Message;
//import org.slf4j.LoggerFactory;
//import org.slf4j.Logger;
//
//import javax.inject.Inject;
//import javax.ws.rs.GET;
//import javax.ws.rs.Path;
//import javax.ws.rs.Produces;
//import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.Response;
//
//@Path("/run")
//public class CommandResource {
//    public static final Logger logger = LoggerFactory.getLogger(CommandResource.class);
//    @Inject
//    CommandProducer commandProducer;
//
//    @GET
//    @Path("/{command}")
//    @Produces(MediaType.TEXT_PLAIN)
//    public Response commandExecutor(@javax.ws.rs.PathParam("command") String command) {
//        logger.info("Command received: " + command + " to execute");
//        commandProducer.toExecute(command);
//        return Response.ok().build();
//    }
//
//}