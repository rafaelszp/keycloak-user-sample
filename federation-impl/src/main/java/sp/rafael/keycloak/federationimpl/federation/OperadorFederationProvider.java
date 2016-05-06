package sp.rafael.keycloak.federationimpl.federation;

import org.keycloak.models.*;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by rafael on 5/4/16.
 */
public class OperadorFederationProvider implements UserFederationProvider {

    protected static final Set<String> supportedCredentialTypes = new HashSet<String>();

    static {
        supportedCredentialTypes.add(UserCredentialModel.PASSWORD);
    }

    public UserModel validateAndProxy(RealmModel realmModel, UserModel userModel) {
        return null;
    }

    public UserModel getUserByUsername(RealmModel realmModel, String username) {
        return null;
    }

    public UserModel getUserByEmail(RealmModel realmModel, String s) {
        return null;
    }

    public List<UserModel> searchByAttributes(Map<String, String> stringStringMap, RealmModel realmModel, int i) {
        return null;
    }

    public List<UserModel> getGroupMembers(RealmModel realmModel, GroupModel groupModel, int i, int i2) {
        return null;
    }

    public void preRemove(RealmModel realmModel) {

    }

    public void preRemove(RealmModel realmModel, RoleModel roleModel) {

    }

    public void preRemove(RealmModel realmModel, GroupModel groupModel) {

    }

    public boolean isValid(RealmModel realmModel, UserModel userModel) {
        return false;
    }

    public Set<String> getSupportedCredentialTypes(UserModel userModel) {
        return null;
    }

    public Set<String> getSupportedCredentialTypes() {
        return null;
    }

    public boolean validCredentials(RealmModel realmModel, UserModel userModel, List<UserCredentialModel> userCredentialModels) {
        return false;
    }

    public boolean validCredentials(RealmModel realmModel, UserModel userModel, UserCredentialModel... userCredentialModels) {
        return false;
    }

    public CredentialValidationOutput validCredentials(RealmModel realmModel, UserCredentialModel userCredentialModel) {
        return null;
    }

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
