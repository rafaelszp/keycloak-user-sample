package sp.rafael.keycloak.federationimpl.federation.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.keycloak.models.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import sp.rafael.keycloak.federationimpl.federation.OperadorFdProvider;
import sp.rafael.keycloak.operadormodel.model.Operador;
import sp.rafael.keycloak.operadormodel.rest.OperadorService;


import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.HashMap;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by rafael on 5/9/16.
 */
@org.junit.FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class OperadorFdProviderTest {


    public static final String PASSWORD = "123456";
    private OperadorFdProvider operadorProvider;

    private static final Operador OP1_TEST = new Operador("rafael souza","e8f58477baf052df4487377ae88a24e1",3729L, "S");

    @Mock
    private KeycloakSession keycloakSession;

    @Mock
    private UserFederationProviderModel model;

    @Mock
    private UserModel userModel;

    @Mock
    private RealmModel realmModel;

    @Mock
    OperadorService service;

    @Mock
    private UserProvider userProvider;

    private Response response;

    @Before
    public void before(){
        MockitoAnnotations.initMocks(this);
        operadorProvider = new OperadorFdProvider(keycloakSession,model,service);
        response = Response.ok(OP1_TEST).build();

        when(userModel.getUsername()).thenReturn(OP1_TEST.getNome());
        when(service.getByLogin(eq(OP1_TEST.getNome()))).thenReturn(response);
        when(keycloakSession.userStorage()).thenReturn(userProvider);
    }

    @Test
    public void t01_shouldValidateAndProxy(){

        assertEquals(userModel,operadorProvider.validateAndProxy(realmModel,userModel));

    }

    @Test
    public void t02_shouldGetUserByUsername(){
        when(keycloakSession.userStorage().addUser(eq(realmModel),eq(OP1_TEST.getNome()))).thenReturn(userModel);

        assertNotNull(operadorProvider.getUserByUsername(realmModel,OP1_TEST.getNome()));

        verify(service).getByLogin(eq(OP1_TEST.getNome()));
        verify(keycloakSession.userStorage()).addUser(eq(realmModel),eq(OP1_TEST.getNome()));
    }

    @Test
    public void t03_shouldNotGetUserByEmail(){
        assertNull(operadorProvider.getUserByEmail(realmModel,null));
    }

    @Test
    public void t04_shouldSearchByAttributes(){
        assertEquals(Collections.emptyList(), operadorProvider.searchByAttributes(new HashMap<String, String>(),realmModel,0));
    }

    @Test
    public void t05_shouldNotGetGroupMembers(){
        assertNull(operadorProvider.getGroupMembers(realmModel,null,0,0));
    }

    @Test
    public void t06_shouldBeValid(){
        when(service.headByLogin(eq(OP1_TEST.getNome()))).thenReturn(response);
        operadorProvider.isValid(realmModel,userModel);
        verify(service,only()).headByLogin(eq(OP1_TEST.getNome()));
    }

    @Test
    public void t07_shouldGetSupportedCredentialTypes(){
        assertEquals(Collections.singleton(UserCredentialModel.PASSWORD),operadorProvider.getSupportedCredentialTypes());
        assertEquals(Collections.singleton(UserCredentialModel.PASSWORD),operadorProvider.getSupportedCredentialTypes(userModel));
    }

    @Test
    public void t08_shouldValidateCredentials(){
        Operador op = new Operador(OP1_TEST.getNome(),OP1_TEST.getSenha2(),0L,null);
        when(service.validateOperador( eq(OP1_TEST.getNome()),any(Operador.class))).thenReturn(response);
        when(userModel.getUsername()).thenReturn(OP1_TEST.getNome());
        operadorProvider.validCredentials(realmModel, userModel, UserCredentialModel.password(PASSWORD));

        verify(service,only()).validateOperador(eq(OP1_TEST.getNome()),any(Operador.class));
        verify(userModel).getUsername();
    }



}
