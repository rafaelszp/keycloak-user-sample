package sp.rafael.keycloak.authenticator.data;


import sp.rafael.keycloak.operadormodel.model.Operador;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by rafael on 5/12/16.
 */
public interface OperadorDao {
    void setupDb();

    EntityManager getEntityManager();

    List<Operador> obterTodos();

    Operador obterPelaSenha2(String senha2);

    Operador obterPeloNome(String username);

}
