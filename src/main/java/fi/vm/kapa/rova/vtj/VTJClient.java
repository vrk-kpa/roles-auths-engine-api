package fi.vm.kapa.rova.vtj;

import fi.vm.kapa.rova.external.model.vtj.VTJResponse;
import fi.vm.kapa.rova.rest.identification.RequestIdentificationInterceptor;
import fi.vm.kapa.rova.rest.validation.ValidationRequestInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jkorkala on 08/03/2017.
 */

@RibbonClient(name="vtjClient")
public class VTJClient implements VTJ {

    @Autowired
    RestTemplate restTemplate;

    private String apiKey;
    private int requestAliveSeconds;
    private String pathPrefix;

    public VTJClient(String apiKey, int requestAliveSeconds, String pathPrefix) {
        this.apiKey = apiKey;
        this.requestAliveSeconds = requestAliveSeconds;
        this.pathPrefix = pathPrefix;
    }

    @PostConstruct
    public void init() {
        List interceptors = new ArrayList();
        interceptors.add(new ValidationRequestInterceptor(apiKey, requestAliveSeconds, pathPrefix));
        interceptors.add(new RequestIdentificationInterceptor("reqID", "user", RequestIdentificationInterceptor.HeaderTrust.TRUST_REQUEST_HEADERS));
        this.restTemplate.setInterceptors(interceptors);

    }

    public VTJResponse getPerson(String hetu, String schema) throws Exception {
        String requestUrl = VTJ_PERSON;
        requestUrl.replace("{schema}", schema);
        requestUrl.replace("{hetu}", hetu);
        VTJResponse response = this.restTemplate.getForObject(requestUrl, VTJResponse.class);
        return response;
    }

}
