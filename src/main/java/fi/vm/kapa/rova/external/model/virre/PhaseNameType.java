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
package fi.vm.kapa.rova.external.model.virre;

import fi.vm.kapa.rova.logging.Logger;

import static fi.vm.kapa.rova.logging.Logger.Field.STACKTRACE;
import static fi.vm.kapa.rova.logging.Logger.Field.WARNINGSTR;

/**
 *  Company problem phases
 */
public enum PhaseNameType {
    NONE, // Undefined
    SAN,  // Saneerausmenettelyssä
    SEL,  // Selvitystilassa
    KON;  // Konkurssissa

    public static final Logger log = Logger.getLogger(PhaseNameType.class);

    /**
     * Parses given phase string into PhaseNameType
     * @param string Phase name
     * @return Parsed phase or NONE if the phase was not identified
     */
    public static PhaseNameType parseType(String string) {
        PhaseNameType value = PhaseNameType.NONE;
        if (string != null) {
            try {
                value = PhaseNameType.valueOf(string.trim().toUpperCase());
            } catch (IllegalArgumentException e) {
                log.warningMap()
                        .set(WARNINGSTR, "Failed to parse phase '" + string+ "': " + e.getMessage())
                        .set(STACKTRACE, Logger.createStackTrace(e))
                        .log();
            }
        }
        return value;
    }

}
