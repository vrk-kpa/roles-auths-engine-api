package fi.vm.kapa.rova.engine.model.hpa;

import java.util.List;

/**
 * Created by jkorkala on 06/06/2017.
 */
public class AuthorizationList extends AbstractAuthorization {
    private List<String> roles;

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
