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
package fi.vm.kapa.rova.notification;

import fi.vm.kapa.rova.RovaRestTemplate;
import fi.vm.kapa.rova.logging.Logger;
import fi.vm.kapa.rova.notification.model.UINotification;
import fi.vm.kapa.rova.rest.identification.RequestIdentificationInterceptor;
import fi.vm.kapa.rova.ui.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import static fi.vm.kapa.rova.logging.Logger.Field.ACTION;

/**
 * Created by jkorkala on 13/03/2017.
 */

@RibbonClient(name = UINotificationClient.CLIENT_NAME)
@Conditional(NotificationClientCondition.class)
public class UINotificationClientImpl implements UINotifications, UINotificationClient {

    private static final Logger LOG = Logger.getLogger(UINotificationClientImpl.class);

    protected static final String RIBBON_ENDPOINT = "http://" + UINotificationClient.CLIENT_NAME;

    @Value("${notification.channel}")
    private String channel;

    @Value("${engine_api_key}")
    private String apiKey;

    @Value("${request_alive_seconds}")
    private Integer requestAliveSeconds;

    @Value("${notification_cache_expiration_in_minutes:5}")
    private long cacheExpirationInMinutes;

    private final NotificationCache<UINotification> notificationCache;

    @Autowired
    @Qualifier("engine-notification-ui")
    private RestTemplate restTemplate;

    @Bean("engine-notification-ui")
    @LoadBalanced
    public RestTemplate getRestTemplate() {
        return new RovaRestTemplate(apiKey, requestAliveSeconds,
                RequestIdentificationInterceptor.HeaderTrust.TRUST_REQUEST_HEADERS);
    }

    public UINotificationClientImpl() {
        notificationCache = new NotificationCache<UINotification>(cacheExpirationInMinutes) {
            @Override
            protected List<UINotification> loadNotifications(Channel channel) {
                List<UINotification> notifications = getChannelUINotifications(channel.toString());
                return notifications;
            }
        };
    }

    @Override
    public List<UINotification> getChannelUINotifications(String channel) {
        String requestUrl = RIBBON_ENDPOINT + CHANNEL_NOTIFICATIONS;
        Map<String, String> params = new HashMap<>();
        params.put(CHANNEL_VARIABLE, channel);
        ResponseEntity<List<UINotification>> response = restTemplate.exchange(requestUrl, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<UINotification>>() {
                }, params);
        return response.getBody();
    }

    @Override
    public List<UINotification> getChannelUINotifications() {

        logNotificationRequest("getActiveNotificationsForChannel");
        try {
            return notificationCache.getNotificationForChannel(Channel.find(channel));
        } catch (ExecutionException ee) {
            LOG.error("Failed to get active notifications for channel " + channel, ee);
            return Collections.emptyList();
        }
    }

    @Override
    public void invalidateCache() {
        notificationCache.invalidateAll();
    }

    private void logNotificationRequest(String action) {
        Logger.LogMap logmap = LOG.infoMap();
        logmap.set(ACTION, action);
        logmap.log();
    }
}
