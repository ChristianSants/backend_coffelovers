package dev.ifrs;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
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

import org.eclipse.microprofile.rest.client.inject.RestClient;

import dev.ifrs.Clients.CafeClient;
import dev.ifrs.Clients.CafeteriaClient;
import dev.ifrs.Clients.LoginClient;
import dev.ifrs.Clients.UserClient;
import dev.ifrs.Clients.UserSaveClient;
import dev.ifrs.Model.Cafe;
import dev.ifrs.Model.Cafeteria;
import dev.ifrs.Model.User;

@Path("/bff")
public class Bff {

    /** ----------- USER ----------- */
    @Inject
    @RestClient
    UserSaveClient userSave;

    @POST
    @Path("/user/save")
    @PermitAll
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public User saveUser(@FormParam("nome") String nome, @FormParam("login") String login, @FormParam("senha") String senha) {
        return userSave.save(nome, login, senha);
    }
    
    @Inject
    @RestClient
    UserClient user;
    
    @GET
    @Path("/user/list")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"User"})
    public List<User> listUser() {
        return user.list();
    }

    @GET
    @Path("/user/list/{id}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"User"})
    public User listUser(@PathParam("id") Long id) {
        return user.find(id);
    }

    @DELETE
    @Path("/user/delete/{id}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"User"})
    public User deleteUser(@PathParam("id") Long id) {
        return user.delete(id);
    }

    @PUT
    @Path("/user/edit")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"User"})
    public User editUser(@FormParam("id") Long id, @FormParam("nome") String nome, @FormParam("senha") String senha) {
        return user.edit(id, nome, senha);
    }

    /** ----------- LOGIN ----------- */
    @Inject
    @RestClient
    LoginClient lg;

    @POST
    @Path("/login")
    // @PermitAll
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public String login(@FormParam("login") String login, @FormParam("senha") String senha){
        return lg.login(login, senha);
    }

    /** ----------- CAFETERIA ----------- */
    @Inject
    @RestClient
    CafeteriaClient cafeteria;

    @POST
    @Path("/cafeteria/save")
    @RolesAllowed({"User"})
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Cafeteria saveCafeteria(@FormParam("nome") String nome, @FormParam("endereco") String endereco ) {
        return cafeteria.save(nome, endereco);
    }
    
    @GET
    @Path("/cafeteria/list")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"User"})
    public List<Cafeteria> listCafeteria() {
        return cafeteria.list();
    }

    @GET
    @Path("/cafeteria/list/{id}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"User"})
    public Cafeteria listCafeteria(@PathParam("id") Long id) {
        return cafeteria.find(id);
    }

    @DELETE
    @Path("/cafeteria/delete/{id}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"User"})
    public Cafeteria deleteCafeteria(@PathParam("id") Long id) {
        return cafeteria.delete(id);
    }

    @PUT
    @Path("/cafeteria/edit")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"User"})
    public Cafeteria editCafeteria(@FormParam("id") Long id, @FormParam("nome") String nome, @FormParam("endereco") String endereco) {
        return cafeteria.edit(id, nome, endereco);
    }

    /** ----------- CAFÉ ----------- */
    @Inject
    @RestClient
    CafeClient cafe;

    @POST
    @Path("/cafe/save")
    @RolesAllowed({"User"})
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Cafe saveCafe(@FormParam("nome") String nome, @FormParam("nota") int nota,  @FormParam("tipo") String tipo, @FormParam("favorito") boolean favorito, @FormParam("user_id") Long user_id, @FormParam("cafeteria_id") Long cafeteria_id) {
        return cafe.save(nome, nota, tipo, favorito, user_id, cafeteria_id);
    }
    
    @GET
    @Path("/cafe/list")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"User"})
    public List<Cafe> listCafe() {
        return cafe.list();
    }

    @GET
    @Path("/cafe/list/{id}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"User"})
    public Cafe listCafe(@PathParam("id") Long id) {
        return cafe.find(id);
    }

    @DELETE
    @Path("/cafe/delete/{id}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"User"})
    public Cafe deleteCafe(@PathParam("id") Long id) {
        return cafe.delete(id);
    }

    @PUT
    @Path("/cafe/edit")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"User"})
    public Cafe editCafe(@FormParam("id") Long id, @FormParam("nome") String nome, @FormParam("nota") int nota,  @FormParam("tipo") String tipo, @FormParam("favorito") boolean favorito) {
        return cafe.edit(id, nome, nota, tipo, favorito);
    }

    @PATCH
    @Path("/cafe/favoritar")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"User"})
    public Cafe favoritarCafe(@FormParam("id") Long id, @FormParam("favorito") boolean favorito) {
        return cafe.favoritarDesfavoritar(id, favorito);
    }

    @GET
    @Path("/cafe/list/user/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"User"})
    public List<Cafe> listByUserId(@PathParam("id") Long id) {
        return cafe.listByUserId(id);
    }

}