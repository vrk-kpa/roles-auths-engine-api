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

import com.netflix.loadbalancer.AbstractServerPredicate;
import com.netflix.loadbalancer.PredicateKey;
import com.netflix.niws.loadbalancer.DiscoveryEnabledServer;
import org.springframework.stereotype.Component;

import javax.annotation.Nullable;
import java.util.Map;

/**
 * Created by jkorkala on 26/03/2017.
 */
public class MetadataAwarePredicate extends AbstractServerPredicate {

    public static final String API_VERSION_METADATA_FIELD = "api-version";

    String apiVersion;

    public MetadataAwarePredicate(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    @Override
    public boolean apply(@Nullable PredicateKey predicateKey) {
        return predicateKey != null
                && predicateKey.getServer() instanceof DiscoveryEnabledServer
                && apply((DiscoveryEnabledServer) predicateKey.getServer());
    }

    protected boolean apply(DiscoveryEnabledServer server) {
        Map<String, String> metadata = server.getInstanceInfo().getMetadata();
        return metadata.get(API_VERSION_METADATA_FIELD) != null && metadata.get(API_VERSION_METADATA_FIELD).equals(apiVersion);
    }

}
