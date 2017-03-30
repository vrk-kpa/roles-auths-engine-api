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
package fi.vm.kapa.rova.engine;

import fi.vm.kapa.rova.admin.model.UserAuthorizationDTO;
import fi.vm.kapa.rova.admin.model.UserDTO;
import fi.vm.kapa.rova.admin.model.UserIdentityDTO;

import java.util.List;

/**
 * Created by mtom on 14/03/2017.
 */
public interface UserAdmin extends Engine {

    String GET_AUTHORIZATION = "/rest/admin/authorization/{userId}/{userIdType}";
    String GET_ALL_USERS = "/rest/admin/users/user/";
    String GET_USER = "/rest/admin/users/user/{uuid}";
    String CREATE_USER = "/rest/admin/users/user/";
    String UPDATE_USER = "/rest/admin/users/user/{uuid}";
    String DELETE_USER = "/rest/admin/users/user/{uuid}";
    String GET_USERS_FOR_SERVICE = "/rest/admin/users/service/uuid/{serviceUuid}";
    String GET_IDENTITIES = "/rest/admin/users/identity/{uuid}";
    String CREATE_IDENTITY = "/rest/admin/users/identity/{uuid}";
    String DELETE_IDENTITY = "/rest/admin/users/identity/{id}";
    String GET_AUTHORIZATIONS = "/rest/admin/users/authorization/{uuid}";
    String CREATE_AUTHORIZATION = "/rest/admin/users/authorization/{uuid}";
    String DELETE_AUTHORIZATION = "/rest/admin/users/authorization/{id}";

    List<UserAuthorizationDTO> getAuthorization(String userId, String userIdType, String enduser);
    List<UserDTO> getAllUsers(String enduser);
    UserDTO getUser(String uuid, String enduser);
    UserDTO createUser(String enduser, UserDTO user);
    UserDTO updateUser(String uuid, String enduser, UserDTO user);
    void deleteUser(String uuid, String enduser);
    List<UserDTO> getUsersForService(String serviceUuid, String enduser);
    List<UserIdentityDTO> getIdentities(String uuid, String enduser);
    UserIdentityDTO createIdentity(String enduser, String uuid, UserIdentityDTO identity);
    void deleteIdentity(String id, String enduser);
    List<UserAuthorizationDTO> getAuthorizations(String uuid, String enduser);
    UserAuthorizationDTO createAuthorization(String enduser, String uuid, UserAuthorizationDTO auth);
    void deleteAuthorization(String id, String enduser);

}
