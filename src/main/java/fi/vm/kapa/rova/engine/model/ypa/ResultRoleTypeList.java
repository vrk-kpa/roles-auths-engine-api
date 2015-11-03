
package fi.vm.kapa.rova.engine.model.ypa;

import java.util.ArrayList;
import java.util.Collection;

import fi.vm.kapa.rova.external.model.IResultType;

public class ResultRoleTypeList<ResultRoleType> extends ArrayList<ResultRoleType> implements IResultType {
    
    private static final long serialVersionUID = 1L;
    
    public ResultRoleTypeList() {
        super();
    }
    public ResultRoleTypeList(int size) {
        super(size);
    }
    public ResultRoleTypeList(Collection<ResultRoleType> c) {
        super(c);
    }
}
