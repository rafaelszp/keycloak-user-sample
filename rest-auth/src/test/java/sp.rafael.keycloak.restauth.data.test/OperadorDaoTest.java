package sp.rafael.keycloak.restauth.data.test;

import org.junit.BeforeClass;
import org.junit.Test;
import sp.rafael.keycloak.restauth.data.OperadorDao;
import sp.rafael.keycloak.restauth.model.Operador;


import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by rafael on 5/5/16.
 */
public class OperadorDaoTest {

    static OperadorDao dao;

    @BeforeClass
    public static void setup(){
        dao = new OperadorDao();
    }


    @Test
    public void deveRetornarEntityManager(){
        assertNotNull(dao.getEntityManager());
    }

    @Test
    public void deveSelecionarTodosOsOperadores(){
        List<Operador> lista = dao.obterTodos();
        assertNotNull(lista);
        System.out.println("Encontrados ==> "+lista.size());
    }

    @Test
    public void deveTrazerOperadorPeloPasswordInserido(){
        final String senha2 = "e8f58477baf052df4487377ae88a24e1";
        Operador operador = dao.obterPelaSenha2(senha2);

        assertNotNull(operador);
    }

    @Test
    public void deveTrazerPeloUsername(){
        final String username = "rafael souza";
        Operador ope = dao.obterPeloNome(username);
        assertNotNull(ope);
    }


}
