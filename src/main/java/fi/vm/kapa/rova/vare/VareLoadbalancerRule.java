package fi.vm.kapa.rova.vare;

import fi.vm.kapa.rova.ribbon.MetadataAwareRule;

/**
 * Created by jkorkala on 31/03/2017.
 */
public class VareLoadbalancerRule extends MetadataAwareRule {

    public VareLoadbalancerRule() {
        super(Vare.API_VERSION);
    }
}
