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
package fi.vm.kapa.rova.vare.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import fi.vm.kapa.rova.external.model.ReasonSerializer;

import java.util.Collections;
import java.util.List;

public class MandateResult {
    
    public static final String REASON_NAME_SSN_MISMATCH = "NAME_SSN_MISMATCH";
    public static final String REASON_DELEGATE_CANNOT_RECEIVE_MANDATE = "DELEGATE_CANNOT_RECEIVE_MANDATE";
    public static final String REASON_PRINCIPAL_NOT_COMPETENT = "PRINCIPAL_NOT_COMPETENT";
    public static final String REASON_DELEGATE_NOT_COMPETENT = "DELEGATE_NOT_COMPETENT";
    public static final String REASON_GENERIC = "MANDATE_NOT_ALLOWED";
    public static final String REASON_END_USER_NOT_AUTHORIZED_REPRESENT_PRINCIPAL = "END_USER_NOT_AUTHORIZED_REPRESENT_PRINCIPAL";
    public static final String REASON_MANDATE_ALREADY_CONFIRMED = "MANDATE_ALREADY_CONFIRMED";
    public static final String REASON_BAD_REQUEST = "BAD_REQUEST";

    private boolean success;
    @JsonSerialize(using=ReasonSerializer.class)
    private String reason;
    private String uuid;
    private String uri;
    
    private MandateDTO targetMandate;
    
    private List<MandateDTO> mandatesThatWillBeReplaced = Collections.emptyList();
    
    public MandateResult() {
        // NOP
    }

    public MandateResult(boolean success, String reason, String uuid, String uri) {
        this.success=  success;
        this.reason = reason;
        this.uuid = uuid;
        this.uri = uri;
    }
    
    public MandateResult(boolean success, String reason, String uuid, String uri,
            List<MandateDTO> mandatesThatWillBeReplaced) {
        this(success, reason, uuid, uri);
        this.mandatesThatWillBeReplaced = mandatesThatWillBeReplaced;
    }

    public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
    
    public String getReason() {
        return reason;
    }
    
    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public List<MandateDTO> getMandatesThatWillBeReplaced() {
        return mandatesThatWillBeReplaced;
    }

    public void setMandatesThatWillBeReplaced(List<MandateDTO> mandatesThatWillBeReplaced) {
        this.mandatesThatWillBeReplaced = mandatesThatWillBeReplaced;
    }

    @Override
    public String toString() {
        return "MandateResult [success=" + success + ", reason=" + reason
                + ", uuid=" + uuid + ", uri=" + uri + ", targetMandate="+targetMandate+"]";
    }

    public MandateDTO getTargetMandate() {
        return targetMandate;
    }

    public void setTargetMandate(MandateDTO targetMandate) {
        this.targetMandate = targetMandate;
    }
}
