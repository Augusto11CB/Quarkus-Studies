package bueno.dev;

import bueno.dev.events.v2.PromotionEvent;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletionStage;

public class PromotionProducer {
    public static final Logger logger = LoggerFactory.getLogger(PromotionProducer.class);

    @Channel("promotion-out")
    Emitter<PromotionEvent> emitter;

    public void enqueuePromotion(PromotionEvent promotion) {
        logger.info("Sending promotion %s to Kafka", promotion.getPROMOTIONCODE());

        CompletionStage<Void> stage = emitter.send(promotion);
        stage.whenComplete((v, t) -> {
            if (t != null) {
                t.printStackTrace();
            } else {
                logger.info("Message sent");
            }
        });
        logger.info("Message sent to Kafka");
    }
}
