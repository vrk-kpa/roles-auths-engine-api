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
package fi.vm.kapa.rova.engine.model;

import java.io.Serializable;
import java.util.Objects;

public class Company implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String name;
    private String identifier;

    private Company() {
        // NOP
    }

    // TODO immutable, constructor annotation
    
    public Company(String identifier, String name) {
        this.identifier = identifier;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getIdentifier() {
        return identifier;
    }

    @Override
    public String toString() {
        return "Company [identifier=" + identifier + ", name=" + name + "]";
    }
    
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        
        if (!(other instanceof Company)) {
            return false;
        }
        
        Company company = (Company) other;
        
        return Objects.equals(identifier, company.identifier) && Objects.equals(name, company.name);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(identifier, name);
    }

}
