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
package fi.vm.kapa.rova.engine.model.hpa;

public class DecisionReason {

    private String reasonRule;
    private String reasonValue;
    private ReasonValueType valueType = ReasonValueType.DESCRIPTION;

    public DecisionReason(String reasonRule, String reasonValue, ReasonValueType valueType) {
        this.reasonRule = reasonRule;
        this.reasonValue = reasonValue;
        this.valueType = valueType;
    }

    public DecisionReason(String reasonRule, String reasonValue) { 
        this(reasonRule, reasonValue, ReasonValueType.DESCRIPTION);
    }

    public DecisionReason() {
        // NOP
    }

    public String getReasonRule() {
        return reasonRule;
    }

    public void setReasonRule(String reasonRule) {
        this.reasonRule = reasonRule;
    }

    public String getReasonValue() {
        return reasonValue;
    }

    public void setReasonValue(String reasonValue) {
        this.reasonValue = reasonValue;
    }

    public ReasonValueType getValueType() {
        return valueType;
    }

    public void setValueType(ReasonValueType valueType) {
        this.valueType = valueType;
    }

    public enum ReasonValueType {
        DESCRIPTION,
        EXCEPTION
    }

}
