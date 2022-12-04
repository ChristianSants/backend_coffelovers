package dev.ifrs.Web;

import java.util.Arrays;
import java.util.HashSet;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.jwt.Claims;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import dev.ifrs.Client.UserClient;
import dev.ifrs.Model.User;
import io.smallrye.jwt.build.Jwt;

@Path("/login")
public class LoginWS {
    @Inject
    @RestClient
    UserClient user;

    @POST
    // @PermitAll
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public String login(@FormParam("login") String login, @FormParam("senha") String senha) {
        User meuUser = user.findByLogin(login);        
        if(meuUser != null){
            if(meuUser.getSenha().equals(senha)){
                //chama jwt
                return (String) Jwt.issuer("http://localhost:8081")
                        .upn(meuUser.getLogin())
                        .groups(new HashSet<>(Arrays.asList("User")))
                        .claim(Claims.full_name, meuUser.getLogin())
                        .sign();
            }
        }
        return "false";
    }
  
}