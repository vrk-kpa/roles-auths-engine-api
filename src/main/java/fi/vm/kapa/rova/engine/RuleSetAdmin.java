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
package fi.vm.kapa.rova.engine;

import fi.vm.kapa.rova.admin.model.RuleSetDTO;

import java.util.List;

/**
 * Created by mtom on 14/03/2017.
 */
public interface RuleSetAdmin {

    String GET_RULESETS = "/rest/admin/services/service/{serviceIdType}/{serviceid}/ruleset";
    String GET_RULESET_BY_ID = "/rest/admin/services/ruleset/{rulesetid}";
    String CREATE_RULESET = "/rest/admin/services/service/{serviceIdType}/{serviceid}/ruleset";
    String UPDATE_RULESET = "/rest/admin/services/service/{serviceIdType}/{serviceid}/ruleset";
    String DELETE_RULESET = "/rest/admin/services/ruleset/{rulesetid}";

    List<RuleSetDTO> getRuleSets(String serviceIdType, String serviceId);
    RuleSetDTO getRuleSetById(String ruleSetId);
    RuleSetDTO createRuleSet(String serviceIdType, String serviceId, String endUser, RuleSetDTO ruleSet);
    List<RuleSetDTO> updateRuleSet(String serviceIdType, String serviceId, String endUser, List<RuleSetDTO> ruleSets);
    void deleteRuleSet(String endUser, String ruleSetId);

}
