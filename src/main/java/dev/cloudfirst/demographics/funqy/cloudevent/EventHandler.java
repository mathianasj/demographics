package dev.cloudfirst.demographics.funqy.cloudevent;

import io.quarkus.funqy.Funq;
import io.quarkus.funqy.knative.events.CloudEventMapping;
import io.quarkus.infinispan.client.Remote;
import io.smallrye.mutiny.Uni;

import javax.inject.Inject;

import org.infinispan.client.hotrod.RemoteCache;
import org.jboss.logging.Logger;

import dev.cloudfirst.demographics.eventsource.PersonState;

public class EventHandler {
    @Inject @Remote("myCache")
    RemoteCache<String, PersonState> personState;

    @Inject
    EventMapper eventMapper;
    
    private static final Logger log = Logger.getLogger(EventHandler.class);

    @Funq
    public void myCloudEventGreeting(Person input) {
        log.info("Hello " + input.getName());
    }

    @Funq
    @CloudEventMapping(trigger = "dev.cloudfirst.demographics.funqy.cloudevent.EmailUpdated")
    public Uni<Void> handleEmailUpdated(EmailUpdated emailUpdated) {

        return Uni.createFrom().completionStage(personState.getAsync(emailUpdated.id))
            .onItem().transform(c -> {
                if(c == null) {
                    c = new PersonState();
                }

                eventMapper.mergePersonState(emailUpdated, c);

                return c;
            }).onItem().transform(e -> {
                System.out.println(emailUpdated.id + " -> " + e);
                return personState.putAsync(emailUpdated.id, e);
            })
            .onItem().transform(e -> null);
    }

    @Funq
    @CloudEventMapping(trigger = "dev.cloudfirst.demographics.funqy.cloudevent.PersonCreated")
    public Uni<Void> handlePersonCreated(PersonCreated personCreated) {

        return Uni.createFrom().completionStage(personState.getAsync(personCreated.id))
            .onItem().transform(c -> {
                if(c == null) {
                    c = new PersonState();
                }

                eventMapper.mergePersonState(personCreated, c);

                return c;
            }).onItem().transform(e -> {
                System.out.println(personCreated.id + " -> " + e);
                return personState.putAsync(personCreated.id, e);
            })
            .onItem().transform(e -> null);
    }
}
