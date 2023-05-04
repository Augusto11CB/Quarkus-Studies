package bueno.dev;//package bueno.dev;

import bueno.dev.events.v2.PromotionEvent;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PromotionListener {
    public static final Logger logger = LoggerFactory.getLogger(PromotionListener.class);

    @Incoming("promotion-in")
    public void consume(PromotionEvent event) {
        logger.info("#### Executing received event : " + event.getPROMOTIONCODE());
    }
}
