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

import java.util.ArrayList;
import java.util.List;

public class Channel {
    private Address addresses;
    private String areaType;
    private String id;
    private List<String> issues = new ArrayList<>();
    private List<String> languages;
    private String organizationId;
    private String publishingStatus;
    private List<Label> serviceChannelDescriptions;
    private List<Label> serviceChannelNames;
    private String serviceChannelType;
    private List<ServiceHours> serviceHours;
    private List<WebPage> webPages;

    public Address getAddresses() {
        return addresses;
    }

    public void setAddresses(Address addresses) {
        this.addresses = addresses;
    }

    public String getAreaType() {
        return areaType;
    }

    public void setAreaType(String areaType) {
        this.areaType = areaType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getIssues() {
        return issues;
    }

    public void setIssues(List<String> issues) {
        this.issues = issues;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getPublishingStatus() {
        return publishingStatus;
    }

    public void setPublishingStatus(String publishingStatus) {
        this.publishingStatus = publishingStatus;
    }

    public List<Label> getServiceChannelDescriptions() {
        return serviceChannelDescriptions;
    }

    public void setServiceChannelDescriptions(List<Label> serviceChannelDescriptions) {
        this.serviceChannelDescriptions = serviceChannelDescriptions;
    }

    public List<Label> getServiceChannelNames() {
        return serviceChannelNames;
    }

    public void setServiceChannelNames(List<Label> serviceChannelNames) {
        this.serviceChannelNames = serviceChannelNames;
    }

    public String getServiceChannelType() {
        return serviceChannelType;
    }

    public void setServiceChannelType(String serviceChannelType) {
        this.serviceChannelType = serviceChannelType;
    }

    public List<ServiceHours> getServiceHours() {
        return serviceHours;
    }

    public void setServiceHours(List<ServiceHours> serviceHours) {
        this.serviceHours = serviceHours;
    }

    public List<WebPage> getWebPages() {
        return webPages;
    }

    public void setWebPages(List<WebPage> webPages) {
        this.webPages = webPages;
    }

}
