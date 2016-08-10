package sp.rafael.keycloak.operadormodel.rest;

import sp.rafael.keycloak.operadormodel.model.Operador;

import javax.print.attribute.standard.Media;
import javax.ws.rs.*;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by rafael on 5/12/16.
 */
@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
public interface OperadorService {

    @GET
    public List<Operador> obterTodos();

    @GET
    @Path("{login}")
    Response getByLogin(@PathParam("login") String login);

    @HEAD
    @Path("{login}")
    Response headByLogin(@PathParam("login") String login);

    @POST
    @Path("{login}")
    Response validateOperador(@PathParam("login") String login, Operador operador);


}
