package sp.rafael.keycloak.federationimpl.federation;

import org.keycloak.Config;
import org.keycloak.models.*;

import java.util.Date;
import java.util.Set;

/**
 * Created by rafael on 5/5/16.
 */
public class OperatorFederationProviderFactory implements UserFederationProviderFactory {


    @Override
    public UserFederationProvider getInstance(KeycloakSession keycloakSession, UserFederationProviderModel userFederationProviderModel) {
        return null;
    }

    @Override
    public Set<String> getConfigurationOptions() {
        return null;
    }

    @Override
    public String getId() {
        return null;
    }

    @Override
    public UserFederationSyncResult syncAllUsers(KeycloakSessionFactory keycloakSessionFactory, String s, UserFederationProviderModel userFederationProviderModel) {
        return null;
    }

    @Override
    public UserFederationSyncResult syncChangedUsers(KeycloakSessionFactory keycloakSessionFactory, String s, UserFederationProviderModel userFederationProviderModel, Date date) {
        return null;
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
