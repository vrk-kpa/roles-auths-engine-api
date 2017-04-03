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

import fi.vm.kapa.rova.external.model.ytj.CompanyAuthorizationData;
import fi.vm.kapa.rova.external.model.ytj.CompanyAuthorizationDataRequest;
import fi.vm.kapa.rova.external.model.ytj.CompanyWithStatusDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface YTJ {
    /* If you change api paths remember to change api version too */
    String API_VERSION = "1.0";
    String CLIENT = "roles-auths-ytj-client";
    String COMPANY_AUTHORIZATION_PATH = "/rest/ytj";
    String UPDATED_COMPANIES_PATH = "/rest/ytj/companies/updated/startDate/{startDate}";
    String COMPANIES_PATH = "/rest/ytj/companies";

    ResponseEntity<CompanyAuthorizationData> getCompanyAuthorizationDataResponse(
            CompanyAuthorizationDataRequest request)
            throws Exception;

    ResponseEntity<List<String>> getUpdatedCompaniesResponse(long startDate) throws Exception;

    ResponseEntity<List<CompanyWithStatusDTO>> getCompaniesResponse(List<String> companyIds) throws Exception;

}