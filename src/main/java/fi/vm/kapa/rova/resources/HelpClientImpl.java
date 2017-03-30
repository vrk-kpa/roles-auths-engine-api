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
package fi.vm.kapa.rova.resources;


import fi.vm.kapa.rova.help.HelpDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mtom on 17/03/2017.
 */
@RibbonClient(name = HelpClient.CLIENT)
@Conditional(HelpClientCondition.class)
public class HelpClientImpl extends AbstractClient implements HelpClient {

    @Autowired
    @Qualifier("helpRestTemplate")
    private RestTemplate resourcesRestTemplate;

    @Bean("helpRestTemplate")
    @LoadBalanced
    public RestTemplate getResourcesRestTemplate() {
        return getRestTemplate();
    }

    @Override
    public ResponseEntity<HelpDocument> getHelpResource(String lang, String chanName, String docName) {
        RestTemplate restTemplate = resourcesRestTemplate;
        String requestUrl = "http://"+ CLIENT + GET_HELP_RESOURCE;

        Map<String, String> params = new HashMap<>();
        params.put("lang", lang);
        params.put("channel", chanName);
        params.put("docName", docName);

        ResponseEntity<HelpDocument> entityResponse = restTemplate.getForEntity(requestUrl, HelpDocument.class, params);
        checkStatus(requestUrl, entityResponse);
        return entityResponse;
    }
}
