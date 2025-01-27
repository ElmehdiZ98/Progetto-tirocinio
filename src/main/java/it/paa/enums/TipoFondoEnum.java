package it.paa.enums;

public enum TipoFondoEnum {
    FSE("FSE", "A"),
    FESR("FESR", "B"),
    MULTIFONDO("MULTIFONDO", "M"),
    FEASR("FEASR", "S"),
    ALTRO("ALTRO", "N"),
    FSC("FSC", "C"),
    POC("POC", "P"),
    YEI("YEI", "Y");

    private final String descrizione;
    private final String codice;

    private TipoFondoEnum(String descrizione, String codice) {
        this.descrizione = descrizione;
        this.codice = codice;
    }

    public String getDescrizione() {
        return this.descrizione;
    }

    public String getCodice() {
        return this.codice;
    }
}
