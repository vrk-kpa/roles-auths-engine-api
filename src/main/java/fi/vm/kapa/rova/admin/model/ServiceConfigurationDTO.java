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

package fi.vm.kapa.rova.admin.model;

import java.util.HashSet;
import java.util.Set;

public class ServiceConfigurationDTO {

    private long id;
    private ServiceDTO serviceId;
    private Boolean includeReasons;
    private Boolean allowMultiSelection;
    private Set<String> issueUris = new HashSet<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ServiceDTO getServiceId() {
        return serviceId;
    }

    public void setServiceId(ServiceDTO serviceId) {
        this.serviceId = serviceId;
    }

    public Boolean getIncludeReasons() {
        return includeReasons;
    }

    public void setIncludeReasons(Boolean includeReasons) {
        this.includeReasons = includeReasons;
    }

    public Boolean getAllowMultiSelection() {
        return allowMultiSelection;
    }

    public void setAllowMultiSelection(Boolean allowMultiSelection) {
        this.allowMultiSelection = allowMultiSelection;
    }

	public Set<String> getIssueUris() {
		return issueUris;
	}

	public void setIssueUris(Set<String> issueUris) {
		this.issueUris = issueUris;
	}
}
