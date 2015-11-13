
package fi.vm.kapa.rova.admin.model;

public enum IdentifierType {

    KID("kid"),
    HETU("hetu"),
    SATU("satu"),
    EIDAS_ID("eidasUID"),
    OPPIJA_ID("OppijaID"),
    EDU_PERSON_PRINCIPAL_NAME("edupersonprincipalname"),
    VIRTU_PERSONAL_PRINCIPAL_NAME("virtuPersonalPrincipalName");

    private final String id;

    private IdentifierType(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public String toString() {
        return this.id;
    }

    public static IdentifierType findByValue(String value) throws IllegalArgumentException {
        for (IdentifierType type : IdentifierType.values()) {
            if (type.getId().equals(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("IdentifierType not found for value: " + value + ".");
    }
}
