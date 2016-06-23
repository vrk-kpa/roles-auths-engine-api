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

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import fi.vm.kapa.rova.admin.model.NotificationDTO;
import fi.vm.kapa.rova.ui.Channel;

public abstract class AbstractNotificationClient extends AbstractEngineClient {

    
    public List<NotificationDTO> getNotificationsForChannel(Channel channel) {
        return getGeneric("notifications/channel/" + channel, new GenericType<List<NotificationDTO>>() {});
    }
    
    @Override
    protected <T> T getGeneric(String url, GenericType<T> returnObject) {
        return getResponse(url, null).readEntity(returnObject);
    }
    
    private Response getResponse(String url, Map<String, Object> params) {
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

}
