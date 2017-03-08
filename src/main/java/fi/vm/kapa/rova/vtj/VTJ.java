package fi.vm.kapa.rova.vtj;

import fi.vm.kapa.rova.external.model.vtj.VTJResponse;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Created by jkorkala on 08/03/2017.
 */
public interface VTJ {
    String VTJ_PERSON = "/vtj/person/{schema}/{hetu}";

    public VTJResponse getPerson(String hetu, String schema) throws Exception ;
}
