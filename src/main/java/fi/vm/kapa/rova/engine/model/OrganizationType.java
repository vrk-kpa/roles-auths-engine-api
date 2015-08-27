package fi.vm.kapa.rova.engine.model;

public enum OrganizationType {

    AOK("1"),
    AOY("2"),
    ASH("3"),
    ASY("4"),
    AY("5"),
    ETS("7"),
    ETY("8"),
    HY("9"),
    KOY("10"),
    KVJ("11"),
    KVY("12"),
    KY("13"),
    OK("14"),
    OP("15"),
    OY("16"),
    OYJ("17"),
    SE("80"),
    SEK("82"),
    SEV("81"),
    SL("19"),
    SP("20"),
    TYH("21"),
    VLL("22"),
    VOJ("23"),
    VOY("24"),
    VY("25");

    private String code;

    private OrganizationType(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

    public static OrganizationType find(String code) {
        for (OrganizationType ot : OrganizationType.values()) {
            if (ot.getCode().endsWith(code)) {
                return ot;
            }
        }
        throw new IllegalArgumentException();
    }

}

