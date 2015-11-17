package fi.vm.kapa.rova.admin.model;

public final class UserAuthorizationConsts {

    public static final String ADMIN_API_USER = "SERVICE#ADMIN#ADMIN_API_USER";
    public static final String ADMIN_API_ROLE = "ROLE_SERVICE#ADMIN#ADMIN_API_USER";
    public static final String ADMIN_USER_MGMT = "SERVICE#ANY#USERMGMT";
    public static final String ADMIN_USER_ROLE = "ROLE_SERVICE#ANY#USERMGMT";
    public static final String ADMIN_SERVICE_MGMT = "SERVICE#ANY#SERVICEMGMT";
    public static final String ADMIN_SERVICE_ROLE = "ROLE_SERVICE#ANY#SERVICEMGMT";
    public static final String ADMIN_RULE_MGMT = "SERVICE#ANY#RULEMGMT";
    public static final String ADMIN_RULE_ROLE = "ROLE_SERVICE#ANY#RULEMGMT";

    public final static String API_NAME = "ADMIN";
    public final static String API_USER = "ADMIN_API_USER";
    public final static String SERVICE_PREFIX = "SERVICE";
    public final static String ROLE_PREFIX = "ROLE_SERVICE";
    public final static String RULEMGMT_ROLE = "RULEMGMT";
    public final static String SERVICEMGMT_ROLE = "SERVICEMGMT";
    public final static String USERMGMT_ROLE = "USERMGMT";

    public final static String ANY = "ANY";

    private UserAuthorizationConsts() {
        throw new AssertionError();
    }
}
