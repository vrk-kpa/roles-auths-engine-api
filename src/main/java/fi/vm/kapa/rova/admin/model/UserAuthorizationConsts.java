package fi.vm.kapa.rova.admin.model;

public final class UserAuthorizationConsts {

    public static final String ADMIN_API_USER = "SERVICE#ADMIN#ADMIN_API_USER";
    public static final String ADMIN_API_ROLE = "ROLE_SERVICE#ADMIN#ADMIN_API_USER";

    public final static String API_NAME = "ADMIN";
    public final static String API_USER = "ADMIN_API_USER";
    public final static String SERVICE_PREFIX = "SERVICE";
    public final static String ROLE_PREFIX = "ROLE_SERVICE";
    public final static String ADMIN_ROLE = "admin";

    private UserAuthorizationConsts() {
        throw new AssertionError();
    }
}
