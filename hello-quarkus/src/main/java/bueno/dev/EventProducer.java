//package bueno.dev;
//
//import com.oracle.svm.core.annotate.Inject;
//import io.smallrye.reactive.messaging.kafka.KafkaClientService;
//import org.eclipse.microprofile.reactive.messaging.Channel;
//import org.eclipse.microprofile.reactive.messaging.Emitter;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import javax.enterprise.context.ApplicationScoped;
//import java.util.concurrent.CompletionStage;
//
//@ApplicationScoped
//public class EventProducer {
//    public static final Logger logger = LoggerFactory.getLogger(EventProducer.class);
//    @Inject
//    @Channel("event-out")
//    Emitter<String> commandEmitter;
//
//    @Inject
//    KafkaClientService kafka;
//
//    public void toExecute(String message) {
//
//        CompletionStage<Void> stage = commandEmitter.send(message);
//        stage.whenComplete((v, t) -> {
//            if (t != null) {
//                t.printStackTrace();
//            } else {
//                logger.info("Message sent");
//            }
//        });
//        logger.info("Message sent to Kafka");
//    }
//}
