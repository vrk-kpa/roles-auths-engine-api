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
package fi.vm.kapa.rova.virre;

import fi.vm.kapa.rova.external.model.virre.CompanyPerson;
import fi.vm.kapa.rova.external.model.virre.CompanyRepresentations;
import fi.vm.kapa.rova.external.model.virre.RepresentationRight;
import fi.vm.kapa.rova.rest.exception.WebApplicationException;

public interface Virre {

    /**
     * Increment the API version if you make modifications to the REST interface.
     * The version is used to discover a compatible service from Eureka.
     */
    String API_VERSION = "1.0";
    String CLIENT = "roles-auths-virre-client";

    String GET_COMPANY_PERSON_PATH = "/rest/prh/companies/{socialsec}";
    String GET_REPRESENTATIONS_PATH = "/rest/prh/representations/{businessid}";
    String GET_RIGHTS_PATH = "/rest/prh/rights/{rightlevel}/{socialsec}/{businessid}";

    CompanyPerson getCompanyPerson(String socialsec) throws WebApplicationException;

    CompanyRepresentations getRepresentations(String businessid) throws WebApplicationException;

    RepresentationRight getRights(String socialSec, String businessId, String rightLevel);

}