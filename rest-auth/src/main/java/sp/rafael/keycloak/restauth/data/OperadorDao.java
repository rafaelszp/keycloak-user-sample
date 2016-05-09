package sp.rafael.keycloak.restauth.data;


import javax.annotation.PostConstruct;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.*;
import javax.transaction.*;
import java.util.List;
import sp.rafael.keycloak.restauth.data.OperadorDao;
import sp.rafael.keycloak.restauth.model.Operador;

/**
 * Created by rafael on 5/5/16.
 */
@TransactionManagement(value = TransactionManagementType.BEAN)
public class OperadorDao {

    @RequestScoped
    @PersistenceContext
    protected EntityManager entityManager;
    
    @Inject
    javax.transaction.UserTransaction transaction;

    public OperadorDao(){
        /*EntityManagerFactory emf = Persistence.createEntityManagerFactory("primary");
        entityManager = emf.createEntityManager();*/
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

    public Operador obterPeloNome(String username) {
        Query query = entityManager.createQuery("select o from Operador o where o.nome = :username").setMaxResults(1);
        query.setParameter("username",username);
        try{
            Operador operador = (Operador) query.getSingleResult();
            return operador;
        }catch(NoResultException nre){
            return null;
        }
    }


    @PostConstruct
    private void setupOperadors() throws SystemException, NotSupportedException, HeuristicRollbackException, HeuristicMixedException, javax.transaction.RollbackException {

        Operador op1 = new Operador("rafael souza","e8f58477baf052df4487377ae88a24e1",3729L);
        Operador op2 = new Operador("keyla morais","aa051fc96a421b599a140b7aafb760c8",1234L);
        Operador op3 = new Operador("maria pereira","8b6c45af8b929995a7276b77a620e5aa",4321L);

        transaction.begin();


        entityManager.persist(op1);
        entityManager.persist(op2);
        entityManager.persist(op3);

        transaction.commit();

    }

}
