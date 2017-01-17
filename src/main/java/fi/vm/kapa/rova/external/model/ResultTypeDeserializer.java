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

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import fi.vm.kapa.rova.engine.model.ypa.IssueRoleType;
import fi.vm.kapa.rova.engine.model.ypa.ResultRoleType;

import static fi.vm.kapa.rova.external.model.AuthorizationType.ALLOWED;
import static fi.vm.kapa.rova.external.model.AuthorizationType.DISALLOWED;
import static fi.vm.kapa.rova.external.model.AuthorizationType.UNKNOWN;

public class ResultTypeDeserializer extends JsonDeserializer<IResultType> {

    @Override
    public IResultType deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        ObjectCodec oc = jp.getCodec();
        JsonNode rootNode = oc.readTree(jp);

        IResultType result = null;
        String nodeAsText = rootNode.asText();
        if (nodeAsText.startsWith("http")) {
            result = new IssueRoleType(nodeAsText);
        } else if (nodeAsText.equals(ALLOWED.toString()) || nodeAsText.equals(DISALLOWED.toString())
                || nodeAsText.equals(UNKNOWN.toString())) {
            result = AuthorizationType.valueOf(nodeAsText);
        } else {
            result = ResultRoleType.valueOf(nodeAsText);
        }

        return result;
    }

}
