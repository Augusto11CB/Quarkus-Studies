package bueno.dev;

import bueno.dev.events.CommandEvent;
import com.oracle.svm.core.annotate.Inject;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EventProducer {
    @Inject
    @Channel("event-out")
    Emitter<String> commandEmitter;
    public void toExecute(String message) {
        commandEmitter.send(message);
    }
}
