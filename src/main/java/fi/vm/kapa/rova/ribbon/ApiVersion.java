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

import fi.vm.kapa.rova.logging.Logger;

public class ApiVersion {

    private static final Logger LOG = Logger.getLogger(ApiVersion.class);

    private int majorVersion;
    private int minorVersion;

    private ApiVersion(int majorVersion, int minorVersion) {
        this.majorVersion = majorVersion;
        this.minorVersion = minorVersion;
    }

    public int getMajorVersion() {
        return majorVersion;
    }

    public int getMinorVersion() {
        return minorVersion;
    }

    public static ApiVersion parseApiVersion(String versionString) throws IllegalArgumentException {
        ApiVersion version = null;
        if (versionString.matches("\\d+\\.\\d+(\\.\\d[^.]*)?")) {
            String [] versionItems = versionString.split("\\.");
            version = new ApiVersion(
                    Integer.parseInt(versionItems[0]),
                    Integer.parseInt(versionItems[1])
            );
        } else {
            throw new IllegalArgumentException("Invalid API version format: " + versionString);
        }
        return version;
    }

    public boolean isCompatibleWith(ApiVersion serverVersion) {
        LOG.debug("Client API version: " + this.toString() + ", server API version: " + serverVersion + ".");
        return serverVersion != null
                && this.majorVersion == serverVersion.majorVersion
                && this.minorVersion <= serverVersion.minorVersion;
    }

    @Override
    public String toString() {
        return "" + majorVersion + "." + minorVersion;
    }

}
