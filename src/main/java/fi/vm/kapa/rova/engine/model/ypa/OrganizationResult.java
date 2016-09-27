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
package fi.vm.kapa.rova.engine.model.ypa;

import java.util.HashSet;
import java.util.Set;

public class OrganizationResult {
    private String name;
    private String identifier;
    private boolean complete;
    private Set<ResultRoleType> roles = new HashSet<ResultRoleType>();

    public OrganizationResult() {
        // NOP
    }

    public OrganizationResult(String name, String identifier, Set<ResultRoleType> roles, boolean complete) {
        this.name = name;
        this.identifier = identifier;
        this.roles = roles;
        this.complete = complete;
    }

    public OrganizationResult(String name, String identifier, Set<ResultRoleType> roles) {
        this.name = name;
        this.identifier = identifier;
        this.roles = roles;
        this.complete = true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public Set<ResultRoleType> getRoles() {
        return roles;
    }

    public void setRoles(Set<ResultRoleType> roles) {
        this.roles = roles;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    @Override
    public String toString() {
        return "{name=" + name + ", identifier=" + identifier + ", roles=" + roles + "}";
    }

}
