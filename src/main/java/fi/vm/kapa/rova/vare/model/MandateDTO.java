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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MandateDTO {
    private String uuid;
    private LegalSubjectDTO delegate = new LegalSubjectDTO();
    private LegalSubjectDTO principal = new LegalSubjectDTO();
    private String subject;
    private Date startDate;
    private Date endDate;
    private List<IssueDTO> issues = new ArrayList<>();
    private String additionalInfo;
    private String groupId;
    
    private String delegateBirth;
    private String principalBirth;
    private boolean valid;
    private boolean principalRepresented;
    private boolean periodOk;
    private boolean request;
    private MandateType type;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public LegalSubjectDTO getDelegate() {
        return delegate;
    }

    public void setDelegate(LegalSubjectDTO delegateDto) {
        this.delegate = delegateDto;
    }

    public LegalSubjectDTO getPrincipal() {
        return principal;
    }

    public void setPrincipal(LegalSubjectDTO principalDto) {
        this.principal = principalDto;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
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

    public List<IssueDTO> getIssues() {
        return issues;
    }

    public void setIssues(List<IssueDTO> issues) {
        this.issues = issues;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public String getDelegateBirth() {
        return delegateBirth;
    }

    public void setDelegateBirth(String delegateBirth) {
        this.delegateBirth = delegateBirth;
    }

    public String getPrincipalBirth() {
        return principalBirth;
    }

    public void setPrincipalBirth(String principalBirth) {
        this.principalBirth = principalBirth;
    }

    public boolean isPrincipalRepresented() {
        return principalRepresented;
    }

    public void setPrincipalRepresented(boolean principalRepresented) {
        this.principalRepresented = principalRepresented;
    }

    public boolean isPeriodOk() {
        return periodOk;
    }

    public void setPeriodOk(boolean periodOk) {
        this.periodOk = periodOk;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public boolean isRequest() {
        return request;
    }

    public void setRequest(boolean request) {
        this.request = request;
    }

    public MandateType getType() {
        return type;
    }

    public MandateDTO setType(MandateType type) {
        this.type = type;
        return this;
    }

    @Override
    public String toString() {
        return "MandateDTO [uuid=" + uuid + ", delegate=" + delegate + ", principal=" + principal + ", subject="
                + subject + ", startDate=" + startDate + ", endDate=" + endDate + ", issues=" + issues
                + ", additionalInfo=" + additionalInfo + ", groupId=" + groupId + ", delegateBirth=" + delegateBirth
                + ", principalBirth=" + principalBirth + ", valid=" + valid + ", principalRepresented="
                + principalRepresented + ", periodOk=" + periodOk + ", request=" + request + ", type=" + type + "]";
    }

}
