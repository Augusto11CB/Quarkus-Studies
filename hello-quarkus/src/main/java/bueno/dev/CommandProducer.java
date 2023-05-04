//package bueno.dev;
//
//import bueno.dev.events.CommandEvent;
//import com.oracle.svm.core.annotate.Inject;
//import org.eclipse.microprofile.reactive.messaging.Channel;
//import org.eclipse.microprofile.reactive.messaging.Emitter;
//
//import javax.enterprise.context.ApplicationScoped;
//
//@ApplicationScoped
//public class CommandProducer {
//    @Inject
//    @Channel("command")
//    Emitter<CommandEvent> commandEmitter;
//    public void toExecute(String message) {
//        commandEmitter.send(CommandEvent.newBuilder().setCOMMAND(message).build());
//    }
//}
