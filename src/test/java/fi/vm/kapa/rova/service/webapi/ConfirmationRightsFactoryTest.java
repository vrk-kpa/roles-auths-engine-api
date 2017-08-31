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
package fi.vm.kapa.rova.service.webapi;

import fi.vm.kapa.rova.vare.model.ConfirmationRights;
import fi.vm.kapa.rova.vare.model.ConfirmationRightsFactory;
import fi.vm.kapa.rova.vare.model.MandateType;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ConfirmationRightsFactoryTest {

    @Test
    public void testTJ() {
        List<String> roles = new ArrayList<>();
        roles.add("TJ");
        ConfirmationRights rights = ConfirmationRightsFactory.fromOrganizationRoles(roles);
        Assert.assertEquals(4, rights.getConfirmationRights().size());
        Assert.assertTrue(rights.getConfirmationRights().contains(MandateType.MANDATE));
        Assert.assertTrue(rights.getConfirmationRights().contains(MandateType.PROXY));
        Assert.assertTrue(rights.getConfirmationRights().contains(MandateType.ASSIGNMENT));
        Assert.assertTrue(rights.getConfirmationRights().contains(MandateType.ASSIGNMENT_PROXY));
    }

    @Test
    public void testNimko() {
        List<String> roles = new ArrayList<>();
        roles.add("NIMKO");
        ConfirmationRights rights = ConfirmationRightsFactory.fromOrganizationRoles(roles);
        Assert.assertEquals(4, rights.getConfirmationRights().size());
        Assert.assertTrue(rights.getConfirmationRights().contains(MandateType.MANDATE));
        Assert.assertTrue(rights.getConfirmationRights().contains(MandateType.PROXY));
        Assert.assertTrue(rights.getConfirmationRights().contains(MandateType.ASSIGNMENT));
        Assert.assertTrue(rights.getConfirmationRights().contains(MandateType.ASSIGNMENT_PROXY));
    }

    @Test
    public void testAssigment() {
        List<String> roles = new ArrayList<>();
        roles.add("http://assignment.valtuudet.suomi.fi/valtuus/123");
        ConfirmationRights rights = ConfirmationRightsFactory.fromOrganizationRoles(roles);
        Assert.assertEquals(1, rights.getConfirmationRights().size());
        Assert.assertTrue(rights.getConfirmationRights().contains(MandateType.MANDATE_RESTRICTED));
        Assert.assertEquals(rights.getAllowedMandateIssues().size(), 1);
        Assert.assertEquals(rights.getAllowedMandateIssues().get(0), "http://valtuudet.suomi.fi/valtuus/123");
    }

    @Test
    public void testAssigmentProxy() {
        List<String> roles = new ArrayList<>();
        roles.add("http://assignmentproxy.valtuudet.suomi.fi/valtuus/123");
        ConfirmationRights rights = ConfirmationRightsFactory.fromOrganizationRoles(roles);
        Assert.assertEquals(1, rights.getConfirmationRights().size());
        Assert.assertTrue(rights.getConfirmationRights().contains(MandateType.PROXY_RESTRICTED));
        Assert.assertEquals(rights.getAllowedMandateIssues().size(), 0);
        Assert.assertEquals(rights.getAllowedProxyMandateIssues().size(), 1);
        Assert.assertEquals(rights.getAllowedProxyMandateIssues().get(0), "http://valtuudet.suomi.fi/valtuus/123");
    }
}
