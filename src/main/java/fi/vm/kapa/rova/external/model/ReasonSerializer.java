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
package fi.vm.kapa.rova.external.model;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import fi.vm.kapa.rova.vare.model.MandateResult;

import java.io.IOException;

/**
 * Auxiliary serializer for preventing, that sensitive reason strings are sent where they should not appear(e.g. vare UI).
 * @author thomas
 *
 */
public class ReasonSerializer extends JsonSerializer<String>  {

    @Override
    public void serialize(String value, JsonGenerator gen, SerializerProvider serializers)
            throws IOException, JsonProcessingException {

        if (!isAllowed(value)) {
            gen.writeString(MandateResult.REASON_GENERIC);    
        } else {
            gen.writeString(value);
        }
    }


    public boolean isAllowed(String value) {
        return MandateResult.REASON_NAME_SSN_MISMATCH.equals(value) ||
                MandateResult.REASON_END_USER_NOT_AUTHORIZED_REPRESENT_PRINCIPAL.equals(value) ||
                MandateResult.REASON_GENERIC.equals(value) ||
                MandateResult.REASON_MANDATE_ALREADY_CONFIRMED.equals(value) ||
                MandateResult.REASON_DELEGATE_CANNOT_RECEIVE_MANDATE.equals(value);

    }
}
