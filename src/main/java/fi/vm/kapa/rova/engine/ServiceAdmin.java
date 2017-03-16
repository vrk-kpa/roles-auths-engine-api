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

import fi.vm.kapa.rova.admin.model.ClientDetailDTO;
import fi.vm.kapa.rova.admin.model.ServiceDTO;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by mtom on 14/03/2017.
 */
public interface ServiceAdmin {

    String GET_SERVICES = "/rest/admin/services/service";
    String GET_CLIENT_DETAILS = "/rest/admin/services/webApiClients/{since}";
    String GET_LAST_SERVICE_UPDATE = "/rest/admin/services/service/lastUpdate";
    String GET_SERVICE_BY_ID = "/rest/admin/services/service/{serviceid}";
    String GET_SERVICE_BY_SERVICE_ID = "/rest/admin/services/service/{serviceIdType}/{serviceIdentifier}";
    String CREATE_SERVICE = "/rest/admin/services/service";
    String UPDATE_SERVICE = "/rest/admin/services/service";
    String DELETE_SERVICE = "/rest/admin/services/service/{serviceid}";
    String GET_ALL_SERVICES= "/rest/admin/services/all/{userName}";
    String GET_SERVICE_PROPERTIES = "/rest/admin/services/service/{serviceIdType}/{serviceIdentifier}/{lang}/properties";

    List<ServiceDTO> getServices(String endUser);
    Map<String, ClientDetailDTO> getClientDetails(long since);
    Date getLastServiceUpdate();
    ServiceDTO getServiceById(String serviceId);
    ServiceDTO getServiceByServiceIdentifier(String serviceIdType, String serviceIdentifier);
    ServiceDTO createService(String endUser, ServiceDTO service);
    ServiceDTO updateService(String endUser, ServiceDTO service);
    void deleteService(String endUser, String serviceId);
    List<ServiceDTO> getAllServices(String username);
    Map<String, String> getServiceProperties(String lang, String serviceIdType, String serviceIdentifier);

}
