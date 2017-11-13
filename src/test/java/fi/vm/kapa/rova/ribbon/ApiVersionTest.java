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
package fi.vm.kapa.rova.ribbon;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ApiVersionTest {

    @Test
    public void testGetVersionNumbers() {
        ApiVersion version = ApiVersion.parseApiVersion("1.23");
        assertEquals(1, version.getMajorVersion());
        assertEquals(23, version.getMinorVersion());
    }

    @Test
    public void testValidApiVersions() {
        ApiVersion.parseApiVersion("1.23");
        ApiVersion.parseApiVersion("1.23.4");
        ApiVersion.parseApiVersion("1.23.4-RC1");
    }

    @Test
    public void testCompatibleApiVersions() {
        assertTrue(ApiVersion.parseApiVersion("1.23")
                .isCompatibleWith(ApiVersion.parseApiVersion("1.23")));
        assertTrue(ApiVersion.parseApiVersion("1.23")
                .isCompatibleWith(ApiVersion.parseApiVersion("1.24.1-RC1")));
    }

    @Test
    public void testIncompatibleApiVersions() {
        assertFalse(ApiVersion.parseApiVersion("1.0")
                .isCompatibleWith(ApiVersion.parseApiVersion("2.0")));
        assertFalse(ApiVersion.parseApiVersion("2.0")
                .isCompatibleWith(ApiVersion.parseApiVersion("1.0")));
        assertFalse(ApiVersion.parseApiVersion("1.24")
                .isCompatibleWith(ApiVersion.parseApiVersion("1.23")));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidApiVersion1() {
        ApiVersion.parseApiVersion("1");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidApiVersion2() {
        ApiVersion.parseApiVersion("1.");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidApiVersion3() {
        ApiVersion.parseApiVersion("1.A");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidApiVersion4() {
        ApiVersion.parseApiVersion("1.23.");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidApiVersion5() {
        ApiVersion.parseApiVersion("1.23.A");
    }

}
