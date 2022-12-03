package dev.ifrs.Client;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import dev.ifrs.Model.User;

import javax.annotation.security.PermitAll;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import java.util.List;
import java.util.Set;

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
@RegisterRestClient(baseUri = "http://localhost:8083/user")
public interface UserClient {
    @POST
    // @PermitAll
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/findbylogin")
    public User findByLogin(@FormParam("login") String login); 
}
