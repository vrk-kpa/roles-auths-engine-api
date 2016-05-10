/**
 * The MIT License
 * Copyright (c) 2016 Population Register Centre
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
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
