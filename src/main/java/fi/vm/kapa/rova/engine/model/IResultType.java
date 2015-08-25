
package fi.vm.kapa.rova.engine.model;

public interface IResultType {
    default public String getResultString() {
        return this.toString();
    }
}
