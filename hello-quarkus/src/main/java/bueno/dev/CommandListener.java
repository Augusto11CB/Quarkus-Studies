package bueno.dev;

import io.smallrye.reactive.messaging.kafka.IncomingKafkaRecord;
import org.eclipse.microprofile.reactive.messaging.Acknowledgment;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@ApplicationScoped
public class CommandListener {
    public static final Logger logger = LoggerFactory.getLogger(CommandResource.class);

    @Incoming("command-in")
    @Acknowledgment(Acknowledgment.Strategy.NONE)
    public CompletableFuture<CompletionStage<Void>> consume(IncomingKafkaRecord<Integer, String> message) {
        logger.info("Executing received command : " + message.getPayload());
        return CompletableFuture.completedFuture(message.ack());
    }
}
