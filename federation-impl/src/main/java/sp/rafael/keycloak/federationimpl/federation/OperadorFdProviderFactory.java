package sp.rafael.keycloak.federationimpl.federation;

import org.jboss.logging.Logger;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.keycloak.Config;
import org.keycloak.models.*;
import sp.rafael.keycloak.operadormodel.rest.OperadorService;

import java.util.Collections;
import java.util.Date;
import java.util.Properties;
import java.util.Set;

/**
 * Created by rafael on 5/5/16.
 */
public class OperadorFdProviderFactory implements UserFederationProviderFactory {

    private static final Logger LOG = Logger.getLogger(OperadorFdProviderFactory.class);
    private static final String PROVIDER_NAME="OPERADOR API PROVIDER";
    private static final String URI_AUTH_SERVICE="http://localhost:8080/authenticator/api/operadores";
    private static Properties properties;


    //private OperadorDao dao;

    private OperadorService service;


    private OperadorService buildService(String uri){

        ResteasyClient client = new ResteasyClientBuilder().disableTrustManager().build();
        ResteasyWebTarget target = client.target(uri);
        return target.proxyBuilder(OperadorService.class).classloader(OperadorService.class.getClassLoader()).build();
    }

    @Override
    public UserFederationProvider getInstance(KeycloakSession session, UserFederationProviderModel model) {
        service = buildService(URI_AUTH_SERVICE);
        return new OperadorFdProvider(session, model, service);
    }

    @Override
    public Set<String> getConfigurationOptions() {
        return Collections.emptySet();
    }

    @Override
    public String getId() {
        return PROVIDER_NAME;
    }

    @Override
    public UserFederationSyncResult syncAllUsers(KeycloakSessionFactory keycloakSessionFactory, String realmId, UserFederationProviderModel userFederationProviderModel) {
        LOG.errorf("Sync All Users isn't implemented yet: %s",realmId);
        throw new UnsupportedOperationException("This federation provider doesn't support syncAllUsers()");
    }

    @Override
    public UserFederationSyncResult syncChangedUsers(KeycloakSessionFactory keycloakSessionFactory, String s, UserFederationProviderModel userFederationProviderModel, Date date) {
        LOG.errorf("Sync Changed Users isn't implemented yet: %s",s);
        throw new UnsupportedOperationException("This federation provider doesn't support syncChangedUsers()");
    }

    @Override
    public UserFederationProvider create(KeycloakSession keycloakSession) {
        return null;
    }

    @Override
    public void init(Config.Scope scope) {

    }

    @Override
    public void postInit(KeycloakSessionFactory keycloakSessionFactory) {

    }

    @Override
    public void close() {

    }
}
