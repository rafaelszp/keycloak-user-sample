package sp.rafael.keycloak.federationimpl.federation;

import org.apache.http.HttpStatus;
import org.jboss.logging.Logger;
import org.keycloak.models.*;
import sp.rafael.keycloak.operadormodel.model.Operador;
import sp.rafael.keycloak.operadormodel.rest.OperadorService;
import sp.rafael.keycloak.operadormodel.util.MD5;

import javax.ws.rs.core.Response;
import java.util.*;

/**
 * Created by rafael on 5/4/16.
 */
public class OperadorFdProvider implements UserFederationProvider {

    private static final Logger LOG = Logger.getLogger(OperadorFdProvider.class);
    protected static final Set<String> supportedCredentialTypes = new HashSet<String>();
    private KeycloakSession session;
    private UserFederationProviderModel model;
    //private OperadorDao dao;
    private OperadorService service;


    public OperadorFdProvider(KeycloakSession session, UserFederationProviderModel model, OperadorService service) {
        this.session = session;
        this.model = model;
        this.service = service;
    }

    public OperadorFdProvider() {
    }

    static {
        supportedCredentialTypes.add(UserCredentialModel.PASSWORD);
    }

    public UserModel validateAndProxy(RealmModel realmModel, UserModel userModel) {
        return userModel;
    }

    private UserModel createUserModel(RealmModel realmModel, Operador operador){

        LOG.infof("Creating user Model");
        UserModel userModel = session.userStorage().addUser(realmModel,operador.getNome());
        userModel.setFederationLink(model.getId());
        userModel.setEnabled(operador.getAtivo().equalsIgnoreCase("s"));

        return userModel;
    }

    public UserModel getUserByUsername(RealmModel realmModel, String username) {
        LOG.infof("getUserByUsername(model,'%s')",username);
        Operador ope = (Operador) service.getByLogin(username).getEntity();
        if(ope!=null) {
            LOG.infof("Got operador %s with password %s", ope.getNome(), ope.getSenha2());
        }else{
            LOG.errorf("Operador not found with username '%s'",username);
        }
        return this.createUserModel(realmModel,ope);
    }

    public UserModel getUserByEmail(RealmModel realmModel, String s) {
        return null;
    }

    public List<UserModel> searchByAttributes(Map<String, String> attributes, RealmModel realmModel, int maxResults) {
        LOG.infof("Search by attributes %s",attributes);
        return Collections.emptyList();
    }

    public List<UserModel> getGroupMembers(RealmModel realmModel, GroupModel groupModel, int firstResult, int maxResults) {
        return null;
    }

    public boolean isValid(RealmModel realmModel, UserModel userModel) {
        LOG.infof("Verifying if the user '%s' is valid",userModel.getUsername());
        Response response = service.headByLogin(userModel.getUsername());
        LOG.infof("The user '%s' got the following HTTP STATUS: %d",userModel.getUsername(),response.getStatus());
        return HttpStatus.SC_OK==response.getStatus();
    }

    public Set<String> getSupportedCredentialTypes(UserModel userModel) {
        return supportedCredentialTypes;
    }

    public Set<String> getSupportedCredentialTypes() {
        return supportedCredentialTypes;
    }

    public boolean validCredentials(RealmModel realm, UserModel user, List<UserCredentialModel> input) {

        String username = user.getUsername();
        LOG.infof("Validating credentials inserted: %s", username);
        if(input==null || input.isEmpty()){
            throw new IllegalArgumentException("Invalid credentials");
        }
        UserCredentialModel credentials =  input.get(0);
        credentials.setValue(MD5.crypt(username + credentials.getValue()));
        LOG.infof("Inserted password: %s", credentials.getValue());
        Operador operador = new Operador(username,credentials.getValue(),0L,null);
        Response response = service.validateOperador(username,operador);
        boolean valid = HttpStatus.SC_OK==response.getStatus();

        if(valid){
            user.updateCredential(credentials);
            user.setFederationLink(null);
            LOG.infof("User %s is valid",username);
        }

        return valid;
    }

    public boolean validCredentials(RealmModel realmModel, UserModel userModel, UserCredentialModel... input) {
        return validCredentials(realmModel,userModel,Arrays.asList(input));
    }

    public CredentialValidationOutput validCredentials(RealmModel realmModel, UserCredentialModel userCredentialModel) {
        return CredentialValidationOutput.failed();
    }


    //omitido
    public void preRemove(RealmModel realmModel) {

    }

    //omitido
    public void preRemove(RealmModel realmModel, RoleModel roleModel) {

    }

    //omitido
    public void preRemove(RealmModel realmModel, GroupModel groupModel) {

    }

    //omitido
    public void close() {

    }

    //omitido
    public boolean synchronizeRegistrations() {
        return false;
    }
    //omitido
    public UserModel register(RealmModel realmModel, UserModel userModel) {
        throw new IllegalStateException("Registration not supported");
    }
    //omitido
    public boolean removeUser(RealmModel realmModel, UserModel userModel) {
        throw new IllegalStateException("Remove not supported");
    }
}
