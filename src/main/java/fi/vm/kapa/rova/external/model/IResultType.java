
package fi.vm.kapa.rova.external.model;

public interface IResultType {
    default String getResultString() {
        return this.toString();
    }
}
