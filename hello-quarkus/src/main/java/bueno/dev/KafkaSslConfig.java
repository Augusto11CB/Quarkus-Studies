package bueno.dev;

import io.quarkus.arc.profile.IfBuildProfile;
import io.smallrye.common.annotation.Identifier;
import org.eclipse.microprofile.config.Config;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Produces;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.StreamSupport;

@ApplicationScoped
public class KafkaSslConfig {

    @Inject
    Config config;
/*
* Doc: https://smallrye.io/smallrye-reactive-messaging/4.5.0/kafka/default-configuration/
* */
    @IfBuildProfile("dev")
    @ApplicationScoped
    @Produces
    @Identifier("my-configuration-aug")
    public Map<String, Object> createKafkaRuntimeConfig() {
        Map<String, Object> properties = new HashMap<>();

        StreamSupport
                .stream(config.getPropertyNames().spliterator(), false)
                .map(String::toLowerCase)
                .filter(name -> name.startsWith("kafka"))
                .distinct()
                .sorted()
                .forEach(name -> {
                    System.out.println("key: " + name);
                    System.out.println("value: " + config.getOptionalValue(name, String.class).orElse(""));
                    System.out.println("###");
                    final String key = name.substring("kafka".length() + 1).toLowerCase().replaceAll("[^a-z0-9.]", ".");
                    final String value = config.getOptionalValue(name, String.class).orElse("");
                    properties.put(key, value);
                });

//        properties.put("mp.messaging.outgoing.event-out.security.protocol", "SSL");
//        properties.put("mp.messaging.outgoing.event-out.schema.registry.url", "http://localhost:8081");
//        properties.put("mp.messaging.outgoing.event-out.ssl.truststore.location", "/tmp/ssl/local-app.truststore.jks");
//        properties.put("mp.messaging.outgoing.event-out.ssl.truststore.password", "changeit");
//        properties.put("mp.messaging.outgoing.event-out.ssl.truststore.type", "JKS");
//        properties.put("mp.messaging.outgoing.event-out.ssl.keystore.location", "/home/acalado/local-app.keystore.jks");
//        properties.put("mp.messaging.outgoing.event-out.ssl.keystore.password", "222");
//        properties.put("mp.messaging.outgoing.event-out.ssl.keystore.type", "JKS");
//        properties.put("mp.messaging.outgoing.event-out.bootstrap.servers", "localhost:9092");
//        properties.put("mp.messaging.outgoing.event-out.connector", "smallrye-kafka");
//        properties.put("mp.messaging.outgoing.event-out.topic", "event-local-6");
//        properties.put("mp.messaging.outgoing.event-out.failure-strategy", "ignore");
//        properties.replace("ssl.keystore.password", "a");
//        properties.replace("security.protocol", "SSL");

        properties.put("security.protocol", "SSL");
        properties.put("ssl.truststore.location", "/tmp/ssl/local-app.truststore.jks");
        properties.put("ssl.truststore.password", "changeit");
        properties.put("ssl.truststore.type", "JKS");
        properties.put("ssl.keystore.location", "/home/acalado/local-app.keystore.jks");
        properties.put("ssl.keystore.password", "changeit");
        properties.put("ssl.keystore.type", "JKS");
        properties.forEach((k, v) -> System.out.println("#####" + k + "=" + v));
        return properties;
    }
}