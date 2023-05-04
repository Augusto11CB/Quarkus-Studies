package bueno.dev;

import bueno.dev.events.v2.PromotionEvent;
import io.quarkus.runtime.configuration.ProfileManager;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/send/promotion")
public class PromotionResource {
    public static final Logger logger = LoggerFactory.getLogger(PromotionResource.class);

    @Inject
    @ConfigProperty(name = "aws.secretsmanager.kafka-certificate-secret") // TODO
    String kafkaSecretName;

    @Inject
    PromotionProducer promotionProducer;

    @GET
    @Path("/{promotion}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response commandExecutor(@javax.ws.rs.PathParam("promotion") String promotion) {
        logger.info("promotion received: " + promotion);
        logger.info("kafkaSecretName: " + kafkaSecretName);
        logger.info("ProfileManager.getActiveProfile(): " + ProfileManager.getActiveProfile());


        final PromotionEvent promotionEvent = PromotionEvent.newBuilder().setPROMOTIONCODE(promotion).build();

        promotionProducer.enqueuePromotion(promotionEvent);
        return Response.ok().build();
    }

}