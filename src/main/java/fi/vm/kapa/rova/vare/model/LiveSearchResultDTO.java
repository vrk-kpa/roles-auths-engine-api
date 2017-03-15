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
package fi.vm.kapa.rova.vare.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class LiveSearchResultDTO {

    private List<LiveSearchMandateDTO> mandates;
    private List<LiveSearchCompanyDTO> companies;
    private List<LiveSearchPersonDTO> persons;

    public LiveSearchResultDTO() {
        mandates = new ArrayList<>();
        companies = new ArrayList<>();
        persons = new ArrayList<>();
    }

    @JsonCreator
    public LiveSearchResultDTO(@JsonProperty("mandates") List<LiveSearchMandateDTO> mandates,
                               @JsonProperty("companies") List<LiveSearchCompanyDTO> companies,
                               @JsonProperty("persons") List<LiveSearchPersonDTO> persons) {
        this.mandates = mandates;
        this.companies = companies;
        this.persons = persons;
    }

    public List<LiveSearchMandateDTO> getMandates() {
        return mandates;
    }

    public List<LiveSearchCompanyDTO> getCompanies() {
        return companies;
    }

    public List<LiveSearchPersonDTO> getPersons() {
        return persons;
    }

}
