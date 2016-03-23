package fi.vm.kapa.rova.admin.model;

public final class UserAuthorizationConsts {

    /**
     * {@value}
     */
    public static final String ADMIN_API_USER = "SERVICE#ADMIN#ADMIN_API_USER";
    /**
     * {@value}
     */
    public static final String ADMIN_API_ROLE = "ROLE_SERVICE#ADMIN#ADMIN_API_USER";
    /**
     * {@value}
     */
    public static final String ADMIN_USER_MGMT = "SERVICE#ANY#USERMGMT";
    /**
     * {@value}
     */
    public static final String ADMIN_USER_ROLE = "ROLE_SERVICE#ANY#USERMGMT";
    /**
     * {@value}
     */
    public static final String ADMIN_SERVICE_MGMT = "SERVICE#ANY#SERVICEMGMT";
    /**
     * {@value}
     */
    public static final String ADMIN_SERVICE_ROLE = "ROLE_SERVICE#ANY#SERVICEMGMT";
    /**
     * {@value}
     */
    public static final String ADMIN_RULE_MGMT = "SERVICE#ANY#RULEMGMT";
    /**
     * {@value}
     */
    public static final String ADMIN_RULE_ROLE = "ROLE_SERVICE#ANY#RULEMGMT";

    /**
     * {@value}
     */
    public final static String API_NAME = "ADMIN";
    /**
     * {@value}
     */
    public final static String API_USER = "ADMIN_API_USER";
    /**
     * {@value}
     */
    public final static String SERVICE_PREFIX = "SERVICE";
    /**
     * {@value}
     */
    public final static String ROLE_PREFIX = "ROLE_SERVICE";
    /**
     * {@value}
     */
    public final static String RULEMGMT_ROLE = "RULEMGMT";
    /**
     * {@value}
     */
    public final static String SERVICEMGMT_ROLE = "SERVICEMGMT";
    /**
     * {@value}
     */
    public final static String USERMGMT_ROLE = "USERMGMT";

    /**
     * {@value}
     */
    public final static String ANY = "ANY";

    private UserAuthorizationConsts() {
        throw new AssertionError();
    }
}
