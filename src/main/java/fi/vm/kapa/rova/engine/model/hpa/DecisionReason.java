package fi.vm.kapa.rova.engine.model.hpa;

public class DecisionReason {

    private String reasonRule;
    private String reasonValue;
    private ReasonValueType valueType = ReasonValueType.DESCRIPTION;

    public DecisionReason(String reasonRule, String reasonValue, ReasonValueType valueType) {
        this.reasonRule = reasonRule;
        this.reasonValue = reasonValue;
        this.valueType = valueType;
    }

    public DecisionReason(String reasonRule, String reasonValue) { 
        this(reasonRule, reasonValue, ReasonValueType.DESCRIPTION);
    }

    public DecisionReason() {
    }

    public String getReasonRule() {
        return reasonRule;
    }

    public void setReasonRule(String reasonRule) {
        this.reasonRule = reasonRule;
    }

    public String getReasonValue() {
        return reasonValue;
    }

    public void setReasonValue(String reasonValue) {
        this.reasonValue = reasonValue;
    }

    public ReasonValueType getValueType() {
        return valueType;
    }

    public void setValueType(ReasonValueType valueType) {
        this.valueType = valueType;
    }

    public enum ReasonValueType {
        DESCRIPTION,
        EXCEPTION
    }

}
