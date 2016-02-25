package fi.vm.kapa.rova.external.model.virre;

import fi.vm.kapa.rova.logging.Logger;
import static fi.vm.kapa.rova.logging.Logger.Field.WARNINGSTR;

/**
 *  Company problem phases
 */
public enum PhaseNameType {
    NONE, // Undefined
    SAN,  // Saneerausmenettelyssä
    SEL,  // Selvitystilassa
    KON;  // Konkurssissa

    public static final Logger log = Logger.getLogger(PhaseNameType.class);

    /**
     * Parses given phase string into PhaseNameType
     * @param string Phase name
     * @return Parsed phase or NONE if the phase was not identified
     */
    public static PhaseNameType parseType(String string) {
        PhaseNameType value = PhaseNameType.NONE;
        if (string != null) {
            try {
                value = PhaseNameType.valueOf(string.trim().toUpperCase());
            } catch (IllegalArgumentException e) {
                log.warningMap()
                    .set(WARNINGSTR, "Failed to parse phase '"
                        + string+ "': " + e.getMessage())
                    .log();
            }
        }
        return value;
    }

}
