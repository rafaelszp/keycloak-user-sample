package sp.rafael.keycloak.authenticator.data;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.jboss.logging.Logger;
import sp.rafael.keycloak.operadormodel.model.Operador;

import javax.annotation.PostConstruct;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by rafael on 5/5/16.
 */
public class OperadorDaoJpa implements OperadorDao {

    @PersistenceContext
    protected EntityManager entityManager;

    private static final Logger LOG = Logger.getLogger(OperadorDaoJpa.class);
    static final String SENHA2 = "e8f58477baf052df4487377ae88a24e1";
    static final String USERNAME = "rafael souza";
//    private SessionFactory sessionFactory;
    
    public OperadorDaoJpa(){

    }

    public OperadorDaoJpa(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public void setupDb(){
        LOG.infof("Inserting user %s with password '%s'",USERNAME,SENHA2);
        Operador op1 = new Operador(USERNAME,SENHA2,0L, "S");
        entityManager.persist(op1);
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public List<Operador> obterTodos() {
        LOG.info("Retrieving all users");
        Query query = getSession().createQuery("select o from Operador o order by o.nome").setMaxResults(100);
        List<Operador> lista = query.list();//query.getResultList();
        return lista;
    }

    @Override
    public Operador obterPelaSenha2(String senha2) {

        LOG.infof("Retrieving user by password '%s'",senha2);
        Session session = getSession();
        Query query = session.createQuery("select o from Operador o where o.senha2 like :pwd").setMaxResults(1);
        query.setParameter("pwd",senha2);
        try{
            Operador operador = (Operador) query.uniqueResult();//query.getSingleResult();
            return operador;
        }catch(Exception nre){
            return null;
        }

    }

    @Override
    public Operador obterPeloNome(String username) {
        LOG.infof("Retrieving user by username '%s'",username);
        Query query = getSession().createQuery("select o from Operador o where o.nome = :username").setMaxResults(1);
        query.setParameter("username", username);
        try {
            Operador operador = (Operador) query.uniqueResult();
            return operador;
        } catch (Exception nre) {
            return null;
        }
    }

    private Session getSession() {
        return (Session) entityManager.getDelegate();
    }


}
