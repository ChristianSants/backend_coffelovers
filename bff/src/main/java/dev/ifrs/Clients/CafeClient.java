package dev.ifrs.Clients;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import dev.ifrs.Model.Cafe;
import io.quarkus.oidc.token.propagation.AccessToken;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
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

@AccessToken
@RegisterRestClient(baseUri = "http://localhost:8083/cafe")
public interface CafeClient {
    @POST
    @Path("/save")
    @RolesAllowed({"User"})
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Cafe save(@FormParam("nome") String nome, @FormParam("nota") int nota,  @FormParam("tipo") String tipo, @FormParam("favorito") boolean favorito, @FormParam("user_id") Long user_id, @FormParam("cafeteria_id") Long cafeteria_id);
    
    @GET
    @Path("/list")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"User"})
    public List<Cafe> list();

    @GET
    @Path("/list/{id}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"User"})
    public Cafe find(@PathParam("id") Long id);

    @DELETE
    @Path("/delete/{id}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"User"})
    public Cafe delete(@PathParam("id") Long id);

    @PUT
    @Path("/edit")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"User"})
    public Cafe edit(@FormParam("id") Long id, @FormParam("nome") String nome, @FormParam("nota") int nota,  @FormParam("tipo") String tipo, @FormParam("favorito") boolean favorito);

    @PATCH
    @Path("/favoritar")
    @RolesAllowed({"User"})
    @Produces(MediaType.APPLICATION_JSON)
    public Cafe favoritarDesfavoritar(@FormParam("id") Long id, @FormParam("favorito") boolean favorito);

    @GET
    @Path("/list/user/{id}")
    @RolesAllowed({"User"})
    @Produces(MediaType.APPLICATION_JSON)
    public List<Cafe> listByUserId(@PathParam("id") Long id);
}
