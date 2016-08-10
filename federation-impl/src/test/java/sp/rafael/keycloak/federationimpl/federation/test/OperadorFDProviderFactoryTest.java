package sp.rafael.keycloak.federationimpl.federation.test;

import org.jboss.logging.Logger;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.keycloak.Config;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;
import org.keycloak.models.UserFederationProvider;
import org.keycloak.models.UserFederationProviderModel;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import sp.rafael.keycloak.federationimpl.federation.OperadorFdProvider;
import sp.rafael.keycloak.federationimpl.federation.OperadorFdProviderFactory;
import sp.rafael.keycloak.operadormodel.rest.OperadorService;

import java.util.Date;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verifyZeroInteractions;

/**
 * Created by rafael on 5/10/16.
 */
@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class OperadorFDProviderFactoryTest {

    private static final Logger LOG = Logger.getLogger(OperadorFDProviderFactoryTest.class);
    private OperadorFdProviderFactory factory;
    private UserFederationProvider provider;

    @Mock
    KeycloakSession keycloakSession;

    @Mock
    UserFederationProviderModel model;

    @Mock
    OperadorService service;

    @Mock
    private Config.Scope config;

    @Mock
    private KeycloakSessionFactory keycloakSessionFactory;



    @Before
    public void setup(){
        LOG.infof("Setup %s test class", this.getClass().getSimpleName());
        MockitoAnnotations.initMocks(this);

        factory = new OperadorFdProviderFactory();
        provider = factory.getInstance(keycloakSession,model);
    }

    @Test
    public void t01_shouldNotBeNull(){
        assertNotNull(factory);
        assertNotNull(provider);
        assertNotNull(service);
        assertTrue(provider instanceof OperadorFdProvider);
    }



    @Test
    public void t02_shouldGetConfigurationOptions(){
        Set<String> options = factory.getConfigurationOptions();
        assertNotNull(options);
        assertTrue(options.isEmpty());
    }

    @Test
    public void t03_shouldGetId(){
        assertNotNull(factory.getId());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void t05_shouldNotSync(){
        factory.syncAllUsers(keycloakSessionFactory,null,model);
        factory.syncChangedUsers(keycloakSessionFactory,null,model,new Date());
    }

    @Test
    public void t06_shouldNotCreate(){
        assertNull(factory.create(keycloakSession));
    }

    @Test
    public void t07_shouldNotInit(){
        factory.init(config);
        verifyZeroInteractions(config);
    }

    @Test
    public void t08_shouldNotPostInit(){
        factory.postInit(keycloakSessionFactory);
        verifyZeroInteractions(keycloakSessionFactory);
    }

    @Test
    public void t09_shouldNotClose(){
        factory.close();
        verifyZeroInteractions(keycloakSessionFactory,keycloakSession);
    }

}
