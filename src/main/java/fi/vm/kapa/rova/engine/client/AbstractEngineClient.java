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
package fi.vm.kapa.rova.engine.client;

import static fi.vm.kapa.rova.rest.exception.ExceptionType.*;
import static fi.vm.kapa.rova.rest.exception.SystemException.Key.DESCRIPTION;

import fi.vm.kapa.rova.logging.Logger;
import fi.vm.kapa.rova.logging.LoggingClientRequestFilter;
import fi.vm.kapa.rova.rest.exception.SystemException;
import fi.vm.kapa.rova.rest.validation.ValidationClientRequestFilter;
import org.apache.commons.lang3.Validate;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.springframework.stereotype.Component;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

@Component
public abstract class AbstractEngineClient  {

    private static final Logger LOG = Logger.getLogger(AbstractEngineClient.class);

    protected abstract String getEngineUrl();
    
    protected abstract String getEngineApiKey();
    
    protected abstract Integer getRequestAliveSeconds();
    
    protected List<Object> getAdditionalJaxrsComponents() {
        return new ArrayList<Object>(0);
    }
    
    protected abstract String getEnduser();
    
    protected Client getClient() {
        ClientConfig clientConfig = new ClientConfig();
        Client client = ClientBuilder.newClient(clientConfig);
        client.register(JacksonFeature.class);
        client.register(new ValidationClientRequestFilter(getEngineApiKey(), getRequestAliveSeconds(), null));
        client.register(new LoggingClientRequestFilter());
        for(Object component: getAdditionalJaxrsComponents()) {
            client.register(component);
        }
        return client;
    }

    protected Map<String, Object> queryParams(String... params) {
        Validate.noNullElements(params);
        Validate.isTrue(params.length % 2 == 0, "Params length not even, can't form key-value pairs");
        Map<String, Object> paramMap = new HashMap<>(params.length / 2);
        for (int i = 0; i < params.length; i = i + 2) {
            paramMap.put(params[i], params[i+1]);
        }
        return paramMap;
    }

    protected <T> T getGeneric(String url, Map<String, Object> params, GenericType<T> returnObject) {
        return getResponse(url, params).readEntity(returnObject);
    }

    protected <T> T getGeneric(String url, GenericType<T> returnObject) {
        return getResponse(url, null).readEntity(returnObject);
    }

    protected <T> T getPlain(String url, Map<String, Object> params, Class<T> clazz) {
        return getResponse(url, params).readEntity(clazz);
    }

    protected <T> T getPlain(String url, Class<T> clazz) {
        return getResponse(url, null).readEntity(clazz);
    }

    protected Response getResponse(String url, Map<String, Object> params) {
        WebTarget webTarget = getClient().target(getEngineUrl() + url);
        if (params != null) {
            for (Entry<String, Object> param : params.entrySet()) {
                webTarget = webTarget.queryParam(param.getKey(), param.getValue());
            }
        }
        Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.get();
        if (response.getStatus() != 200) {
            handleError(response);
        }
        return response;
    }

    protected void handleError(Response response) {
        if (response.hasEntity()) {
            try {
                HashMap<String, Object> errorMap = response.readEntity(new GenericType<HashMap<String, Object>>() {});
                int errorCode = (Integer) errorMap.get("errorCode");

                switch (errorCode) {
                    case 101:
                        throw new SystemException(MISSING_PARAMETER).set(DESCRIPTION, "puuttuva.parametri");

                    case 102:
                        throw new SystemException(MATCHING_SERVICE_NOT_FOUND).set(DESCRIPTION, "palvelua.ei.loytynyt");

                    case 103:
                        throw new SystemException(DUPLICATE_SERVICE_IDENTIFIER).set(DESCRIPTION, "samanniminen.palvelu.on.jo.kannassa");

                    case 108:
                        throw new SystemException(ILLEGAL_RULE_CONFIG).set(DESCRIPTION, "viallinen.saantokonfiguraatio");

                    case 109:
                        throw new SystemException(DUBLICATE_USER_IDENTITY).set(DESCRIPTION, "identiteetti.on.jo.lisatty");

                    default:
                        throw new SystemException(OTHER_EXCEPTION).set(DESCRIPTION, "operaatio.epaonnistui");
                }
            } catch (ClassCastException e) {
                LOG.error("Exception occured while handling error",e);
                throw new SystemException(OTHER_EXCEPTION).set(DESCRIPTION, "operaatio.epaonnistui");
            } catch (ProcessingException e) {
                LOG.error("Exception occured while handling error",e);
                throw new SystemException(OTHER_EXCEPTION).set(DESCRIPTION, "operaatio.epaonnistui");
            } catch (IllegalStateException e) {
                LOG.error("Exception occured while handling error",e);
                throw new SystemException(OTHER_EXCEPTION).set(DESCRIPTION, "operaatio.epaonnistui");
            }  catch (NullPointerException e) {
                LOG.error("Exception occured while handling error",e);
                throw new SystemException(OTHER_EXCEPTION).set(DESCRIPTION, "operaatio.epaonnistui");
            }
        } else {
            throw new SystemException(OTHER_EXCEPTION).set(DESCRIPTION, "operaatio.epaonnistui");
        }
    }
}
