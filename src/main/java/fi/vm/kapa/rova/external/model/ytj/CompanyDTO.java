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
package fi.vm.kapa.rova.external.model.ytj;

import java.util.List;

public class CompanyDTO {
    
    private String companyId;
    private String tradeName;
    private boolean active;
    private List<String> auxiliaryTradeNames;
    private List<String> parallelTradeNames;
    
    public CompanyDTO() {
        super();
    }

    public CompanyDTO(String companyId, String tradeName, boolean active,
                           List<String> auxiliaryTradeNames, List<String> parallelTradeNames) {
        super();
        this.companyId = companyId;
        this.tradeName = tradeName;
        this.active = active;
        this.auxiliaryTradeNames = auxiliaryTradeNames;
        this.parallelTradeNames = parallelTradeNames;
    }

    public CompanyDTO(CompanyWithStatusDTO dto) {
        super();
        this.companyId = dto.getCompanyId();
        this.tradeName = dto.getTradeName();
        this.active = "STATUS3".equals(dto.getPrimaryStatusCode()) && "2".equals(dto.getSecondaryStatusCode());
        this.auxiliaryTradeNames = dto.getAuxiliaryTradeNames();
        this.parallelTradeNames = dto.getParallelTradeNames();
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getTradeName() {
        return tradeName;
    }

    public void setTradeName(String tradeName) {
        this.tradeName = tradeName;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<String> getAuxiliaryTradeNames() {
        return auxiliaryTradeNames;
    }

    public void setAuxiliaryTradeNames(List<String> auxiliaryTradeNames) {
        this.auxiliaryTradeNames = auxiliaryTradeNames;
    }

    public List<String> getParallelTradeNames() {
        return parallelTradeNames;
    }

    public void setParallelTradeNames(List<String> parallelTradeNames) {
        this.parallelTradeNames = parallelTradeNames;
    }

}
