package bueno.dev;

import com.oracle.svm.core.annotate.Inject;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Message;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CommandProducer {
    @Inject
    @Channel("command")
    Emitter<String> commandEmitter;
    public void toExecute(Message<String> message) {
        commandEmitter.send(message.getPayload().toUpperCase());
    }
}
