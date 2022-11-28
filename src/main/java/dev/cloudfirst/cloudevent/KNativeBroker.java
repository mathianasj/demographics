package dev.cloudfirst.cloudevent;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import io.quarkus.funqy.knative.events.CloudEvent;
import io.smallrye.mutiny.Uni;

@Path("/knative-eventsource/default")
@RegisterRestClient
public interface KNativeBroker {
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    void postEvent(CloudEvent<Object> createPerson);

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    Uni<Void> postEventAsync(CloudEvent<Object> cloudEvent);
}
