package sp.rafael.keycloak.restauth.data;


import javax.persistence.*;
import java.util.List;
import sp.rafael.keycloak.restauth.data.OperadorDao;
import sp.rafael.keycloak.restauth.model.Operador;

/**
 * Created by rafael on 5/5/16.
 */
public class OperadorDao {

    protected EntityManager entityManager;

    public OperadorDao(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("primary");
        entityManager = emf.createEntityManager();
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public List<Operador> obterTodos() {

        Query query = entityManager.createQuery("select o from Operador o order by o.nome").setMaxResults(100);
        List<Operador> lista = query.getResultList();
        return lista;
    }

    public Operador obterPelaSenha2(String senha2) {

        Query query = entityManager.createQuery("select o from Operador o where o.senha2 like :pwd").setMaxResults(1);
        query.setParameter("pwd",senha2);
        try{
            Operador operador = (Operador) query.getSingleResult();
            return operador;
        }catch(NoResultException nre){
            return null;
        }

    }
}
