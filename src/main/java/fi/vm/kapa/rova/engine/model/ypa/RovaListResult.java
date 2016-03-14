package fi.vm.kapa.rova.engine.model.ypa;

import java.io.Serializable;
import java.util.*;

public class RovaListResult<T> implements Serializable {

    private int total;
    private int offset;
    private int limit;
    private List<T> contents;

    public RovaListResult() {
        // NOP
    }

    public RovaListResult(int total, int offset, int limit, List<T> contents) {
        this.total = total;
        this.offset = offset;
        this.limit = limit;
        this.contents = contents;
    }

    public List<T> getContents() {
        return contents;
    }

    public int size() {
        return contents.size();
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
