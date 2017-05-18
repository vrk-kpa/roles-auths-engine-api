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
package fi.vm.kapa.rova.ptv.model;

import java.util.List;

public class ServiceHours {
    private String serviceHourType;
    private String validFrom;
    private String validTo;
    private Boolean isClosed;
    private Boolean validForNow;
    private List<Label> additionalInformation;
    private OpeningHour openingHour;

    public String getServiceHourType() {
        return serviceHourType;
    }

    public void setServiceHourType(String serviceHourType) {
        this.serviceHourType = serviceHourType;
    }

    public String getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(String validFrom) {
        this.validFrom = validFrom;
    }

    public String getValidTo() {
        return validTo;
    }

    public void setValidTo(String validTo) {
        this.validTo = validTo;
    }

    public Boolean getIsClosed() {
        return isClosed;
    }

    public void setIsClosed(Boolean isClosed) {
        this.isClosed = isClosed;
    }

    public Boolean getValidForNow() {
        return validForNow;
    }

    public void setValidForNow(Boolean validForNow) {
        this.validForNow = validForNow;
    }

    public List<Label> getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(List<Label> additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    public OpeningHour getOpeningHour() {
        return openingHour;
    }

    public void setOpeningHour(OpeningHour openingHour) {
        this.openingHour = openingHour;
    }

}
