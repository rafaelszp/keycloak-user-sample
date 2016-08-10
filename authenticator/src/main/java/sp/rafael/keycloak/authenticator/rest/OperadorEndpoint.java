package sp.rafael.keycloak.authenticator.rest;

import org.jboss.logging.Logger;
import sp.rafael.keycloak.authenticator.data.OperadorDao;
import sp.rafael.keycloak.authenticator.data.OperadorDaoJpa;
import sp.rafael.keycloak.operadormodel.model.Operador;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;


/**
 * Created by rafael on 5/12/16.
 */

@Path("/operadores")
public class OperadorEndpoint implements sp.rafael.keycloak.operadormodel.rest.OperadorService {

    private static final Logger LOG = Logger.getLogger(OperadorEndpoint.class);

    @Inject
    OperadorDaoJpa dao;

    @Override
    public List<Operador> obterTodos() {
        return dao.obterTodos();
    }

    @Override
    public Response getByLogin(@PathParam("login") String login){
        LOG.infof("GET by Login %s",login);
        Operador operador = dao.obterPeloNome(login);
        if(operador!=null){
            return Response.ok(operador).entity(operador).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @Override
    public Response headByLogin(@PathParam("login") String login){
        LOG.infof("HEAD by Login %s",login);
        Operador operador = dao.obterPeloNome(login);
        if(operador!=null){
            return Response.ok().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @Override
    public Response validateOperador(@PathParam("login") String login,Operador operador){
        LOG.infof("Validate Login %s",login);
        Operador operadorValido = dao.obterPelaSenha2(operador.getSenha2());
        if(operadorValido!=null){
            return Response.ok().build();
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

}
