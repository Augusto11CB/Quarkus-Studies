//package bueno.dev;
//
//import org.eclipse.microprofile.reactive.messaging.Incoming;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import javax.enterprise.context.ApplicationScoped;
//
//@ApplicationScoped
//public class EventListener {
//    public static final Logger logger = LoggerFactory.getLogger(EventListener.class);
//
//    @Incoming("event-in")
//    public void consume(String event) {
//        logger.info("#### Executing received event : " + event);
//    }
//}
