package fi.vm.kapa.rova.external.model;

public enum ServiceIdType {
    XROAD("xroad"), WEB_API("webApi"), UUID("uuid");
    
    private String text;
    
    private ServiceIdType(String text) {
        this.text = text;
    }
    
    public static ServiceIdType fromText(String text) {
        for(ServiceIdType type : values()) {
            if(type.getText().equals(text)) {
                return type;
            }
        }
        throw new IllegalArgumentException("No such ServiceIdType: " + text);
    }
    
    public String getText() {
        return text;
    }
    
    @Override
    public String toString() {
        return text;
    }
}
