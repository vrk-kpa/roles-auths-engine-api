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

public enum IdentifierType {

    KID("kid"),
    HETU("hetu"),
    SATU("satu"),
    EIDAS_ID("eidasUID"),
    OPPIJA_ID("OppijaID"),
    EDU_PERSON_PRINCIPAL_NAME("edupersonprincipalname"),
    VIRTU_PERSONAL_PRINCIPAL_NAME("virtuPersonalPrincipalName");

    private final String id;

    IdentifierType(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public String toString() {
        return this.id;
    }

    public static IdentifierType findByValue(String value) throws IllegalArgumentException {
        for (IdentifierType type : IdentifierType.values()) {
            if (type.getId().equals(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("IdentifierType not found for value: " + value + ".");
    }
}
