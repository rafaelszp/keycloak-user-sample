package sp.rafael.keycloak.authenticator.data.test;



import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Test;
import sp.rafael.keycloak.authenticator.data.OperadorDao;
import sp.rafael.keycloak.authenticator.data.OperadorDaoJpa;
import sp.rafael.keycloak.operadormodel.model.Operador;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by rafael on 5/5/16.
 */
public class OperadorDaoJpaTest {

    static OperadorDaoJpa dao;
    static final String SENHA2 = "e8f58477baf052df4487377ae88a24e1";
    static final String USERNAME = "rafael souza";

    @BeforeClass
    public static void setup(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("primary");
        EntityManager entityManager = emf.createEntityManager();
        dao = new OperadorDaoJpa(entityManager);
        dao.getEntityManager().getTransaction().begin();
        dao.setupDb();
        dao.getEntityManager().getTransaction().commit();
    }

    @Test
    public void deveSelecionarTodosOsOperadores(){
        List<Operador> lista = dao.obterTodos();
        assertNotNull(lista);
        System.out.println("Encontrados ==> "+lista.size());
    }

    @Test
    public void deveTrazerOperadorPeloPasswordInserido(){

        Operador operador = dao.obterPelaSenha2(SENHA2);

        assertNotNull(operador);
    }

    @Test
    public void deveTrazerPeloUsername(){

        Operador ope = dao.obterPeloNome(USERNAME);
        assertNotNull(ope);
    }


}
