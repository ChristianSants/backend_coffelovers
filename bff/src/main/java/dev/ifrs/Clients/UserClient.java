package dev.ifrs.Clients;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import dev.ifrs.Model.User;
import io.quarkus.oidc.token.propagation.AccessToken;

import javax.annotation.security.RolesAllowed;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
// @AccessToken
@RegisterRestClient(baseUri = "http://localhost:8083/user")
public interface UserClient {
    @GET
    @Path("/list")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    // @RolesAllowed({"User"})
    public List<User> list();

    @GET
    @Path("/list/{id}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    // @RolesAllowed({"User"})
    public User find(@PathParam("id") Long id);

    @DELETE
    @Path("/delete/{id}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    // @RolesAllowed({"User"})
    public User delete(@PathParam("id") Long id);

    @PUT
    @Path("/edit")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    // @RolesAllowed({"User"})
    public User edit(@FormParam("id") Long id, @FormParam("nome") String nome, @FormParam("senha") String senha);
}
