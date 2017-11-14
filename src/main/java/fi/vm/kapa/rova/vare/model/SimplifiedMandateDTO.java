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

import java.util.Date;

public class SimplifiedMandateDTO {

    private String uuid;
    private Date startDate;
    private Date endDate;
    private String issueUri;
    private String issue;
    private Boolean isRepresentedPartyPrincipal;
    private Boolean isNew;
    private MandateType type;


    public SimplifiedMandateDTO() {
    }

    public SimplifiedMandateDTO(String uuid, Date startDate, Date endDate, String issueUri, String issue, Boolean isRepresentedPartyPrincipal, Boolean isNew, MandateType type) {
        super();
        this.uuid = uuid;
        this.startDate = startDate;
        this.endDate = endDate;
        this.issueUri = issueUri;
        this.issue = issue;
        this.isRepresentedPartyPrincipal = isRepresentedPartyPrincipal;
        this.isNew = isNew;
        this.type = type;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    
    public String getIssueUri() {
        return issueUri;
    }

    public void setIssueUri(String issueUri) {
        this.issueUri = issueUri;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public Boolean getIsRepresentedPartyPrincipal() {
        return isRepresentedPartyPrincipal;
    }

    public void setIsRepresentedPartyPrincipal(Boolean isRepresentedPartyPrincipal) {
        this.isRepresentedPartyPrincipal = isRepresentedPartyPrincipal;
    }

    public MandateType getType() {
        return type;
    }

    public void setType(MandateType type) {
        this.type = type;
    }

    public Boolean getIsNew() {
        return isNew;
    }

    public void setIsNew(Boolean isNew) {
        this.isNew = isNew;
    }
}
