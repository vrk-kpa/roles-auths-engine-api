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
package fi.vm.kapa.rova.ytj;

import fi.vm.kapa.rova.ClientException;
import fi.vm.kapa.rova.ErrorHandlerBuilder;
import fi.vm.kapa.rova.RovaRestTemplate;
import fi.vm.kapa.rova.external.model.ytj.CompanyAuthorizationData;
import fi.vm.kapa.rova.external.model.ytj.CompanyAuthorizationDataRequest;
import fi.vm.kapa.rova.external.model.ytj.CompanyWithStatusDTO;
import fi.vm.kapa.rova.logging.Logger;
import fi.vm.kapa.rova.rest.identification.RequestIdentificationInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Conditional;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@RibbonClient(name = "ytjClient")
@Conditional(YTJClientCondition.class)
public class YTJClientImpl implements YTJ, YTJClient {

    private static final Logger LOG = Logger.getLogger(YTJClientImpl.class);

    @Value("${ytj_client_api_key}")
    private String apiKey;

    @Value("${request_alive_seconds}")
    private int requestAliveSeconds;

    @Value("${ytj_client_url}")
    private String endpointUrl;

    @Override
    public Optional<CompanyAuthorizationData> getCompanyAuthorizationData(String ssn) {
        CompanyAuthorizationDataRequest request = new CompanyAuthorizationDataRequest();
        request.setSsn(ssn);
        ResponseEntity<CompanyAuthorizationData> entityResponse = getCompanyAuthorizationDataResponse(request);
        if (entityResponse.getStatusCode() == HttpStatus.OK) {
            return Optional.of(entityResponse.getBody());
        } else if (entityResponse.getStatusCode() == HttpStatus.NOT_FOUND) {
            return Optional.empty();
        } else {
            String errorMessage = "YTJ connection error; " + entityResponse.getStatusCode()
                    + " for CompanyAuthorizationData. ssn=" + request;
            LOG.error(errorMessage);
            throw new ClientException(errorMessage);
        }
    }

    @Override
    public List<String> getUpdatedCompanies(long startDate) {
        ResponseEntity<List<String>> responseEntity = getUpdatedCompaniesResponse(startDate);
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            return responseEntity.getBody();
        } else {
            String errorMessage = "YTJ connection error; " + responseEntity.getStatusCode()
                    + " for UpdatedCompanies. startDate=" + startDate;
            LOG.error(errorMessage);
            throw new ClientException(errorMessage);
        }
    }

    @Override
    public List<CompanyWithStatusDTO> getCompanies(List<String> companyIds) {
        ResponseEntity<List<CompanyWithStatusDTO>> responseEntity = getCompaniesResponse(companyIds);

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            return responseEntity.getBody();
        } else {
            String errorMessage = "YTJ connection error; " + responseEntity.getStatusCode() + " for getCompanies";
            LOG.error(errorMessage);
            throw new ClientException(errorMessage);
        }
    }

    @Override
    public ResponseEntity<CompanyAuthorizationData> getCompanyAuthorizationDataResponse(
            CompanyAuthorizationDataRequest request) {
        RestTemplate restTemplate = getRestTemplate();
        String requestUrl = endpointUrl + COMPANY_AUTHORIZATION_PATH;
        ResponseEntity<CompanyAuthorizationData> responseEntity = restTemplate.postForEntity(requestUrl, request,
                CompanyAuthorizationData.class);
        return responseEntity;
    }

    @Override
    public ResponseEntity<List<String>> getUpdatedCompaniesResponse(long startDate) {
        RestTemplate restTemplate = getRestTemplate();
        String requestUrl = endpointUrl + UPDATED_COMPANIES_PATH;
        return restTemplate.exchange(requestUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<String>>() {
        }, new Long(startDate));
    }

    @Override
    public ResponseEntity<List<CompanyWithStatusDTO>> getCompaniesResponse(List<String> companyIds) {
        RestTemplate restTemplate = getRestTemplate();
        String requestUrl = endpointUrl + COMPANIES_PATH;

        HttpEntity<List<String>> postedEntity = new HttpEntity<>(companyIds);

        return restTemplate.exchange(requestUrl, HttpMethod.POST,
                postedEntity, new ParameterizedTypeReference<List<CompanyWithStatusDTO>>() {
                });
    }

    private RestTemplate getRestTemplate() {
        RestTemplate restTemplate = new RovaRestTemplate(apiKey, requestAliveSeconds,
                RequestIdentificationInterceptor.HeaderTrust.TRUST_REQUEST_HEADERS, ErrorHandlerBuilder.allExcept404());
        return restTemplate;
    }

}
