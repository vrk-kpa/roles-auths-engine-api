
package fi.vm.kapa.rova.engine.model;

public interface IResultType {
    default String getResultString() {
        return this.toString();
    }
}
