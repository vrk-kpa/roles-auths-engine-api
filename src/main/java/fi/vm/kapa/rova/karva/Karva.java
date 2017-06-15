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
package fi.vm.kapa.rova.karva;

import fi.vm.kapa.rova.ClientException;
import fi.vm.kapa.rova.karva.model.KarvaResponse;
import fi.vm.kapa.rova.rest.exception.WebApplicationException;
import org.xml.sax.SAXException;

import javax.xml.crypto.MarshalException;
import javax.xml.crypto.dsig.XMLSignatureException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.SOAPException;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

public interface Karva {
    String API_VERSION = "1.0";
    String CLIENT = "roles-auths-karva-client";

    // NOTE: the actual request needs to be of the form /rest/karva/roles/{personId}?entityId={entityId}
    // EntityId is passed in as parameter, because it is formatted like a URL
    String KARVA_ROLES_PATH = "/rest/karva/roles/{personId}";

    KarvaResponse getRoles(String entityId, String personId) throws WebApplicationException, ClientException,
            SAXException, IllegalAccessException, XMLSignatureException, ClassNotFoundException, NoSuchAlgorithmException,
            CertificateException, KeyStoreException, InstantiationException, MarshalException, ParserConfigurationException,
            InvalidAlgorithmParameterException, UnrecoverableKeyException, IOException, SOAPException;
}
