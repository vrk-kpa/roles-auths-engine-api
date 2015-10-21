
package fi.vm.kapa.rova.engine.model;

import java.util.ArrayList;
import java.util.Collection;

public class ResultRoleTypeList<ResultRoleType> extends ArrayList<ResultRoleType> implements IResultType {
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
