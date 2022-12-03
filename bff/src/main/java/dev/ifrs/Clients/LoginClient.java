package dev.ifrs.Clients;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.annotation.security.PermitAll;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * To use it via injection.
 *
 * {@code
 *     @Inject
 *     @RestClient
 *     MyRemoteService myRemoteService;
 *
 *     public void doSomething() {
 *         Set<MyRemoteService.Extension> restClientExtensions = myRemoteService.getExtensionsById("io.quarkus:quarkus-rest-client");
 *     }
 * }
 */
@RegisterRestClient(baseUri = "http://localhost:8081/login")
public interface LoginClient {
    @POST
    // @PermitAll
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public String login(@FormParam("login") String login, @FormParam("senha") String senha);    
}
