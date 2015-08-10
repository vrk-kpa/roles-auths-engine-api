package fi.vm.kapa.rova.rest.validation;

import java.util.List;

public class ValidationConfiguration {

    String path;
    List<String> requiredParameters;
    List<String> optionalParameters;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<String> getRequiredParameters() {
        return requiredParameters;
    }

    public void setRequiredParameters(List<String> requiredParameters) {
        this.requiredParameters = requiredParameters;
    }

    public List<String> getOptionalParameters() {
        return optionalParameters;
    }

    public void setOptionalParameters(List<String> optionalParameters) {
        this.optionalParameters = optionalParameters;
    }

}
