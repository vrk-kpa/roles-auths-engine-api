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

public class PtvService {

    private String id;
    private List<Label> names = new ArrayList<>();
    private List<Label> descriptions = new ArrayList<>();
    private Organization responsibleOrganization;
    private List<Channel> channels = new ArrayList<>();
    private List<LifeEvent> lifeEvents = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Label> getNames() {
        return names;
    }

    public void setNames(List<Label> names) {
        this.names = names;
    }

    public List<Label> getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(List<Label> descriptions) {
        this.descriptions = descriptions;
    }

    public Organization getResponsibleOrganization() {
        return responsibleOrganization;
    }

    public void setResponsibleOrganization(Organization responsibleOrganization) {
        this.responsibleOrganization = responsibleOrganization;
    }

    public List<Channel> getChannels() {
        return channels;
    }

    public void setChannels(List<Channel> channels) {
        this.channels = channels;
    }

    public List<LifeEvent> getLifeEvents() {
        return lifeEvents;
    }

    public void setLifeEvents(List<LifeEvent> lifeEvents) {
        this.lifeEvents = lifeEvents;
    }


}
