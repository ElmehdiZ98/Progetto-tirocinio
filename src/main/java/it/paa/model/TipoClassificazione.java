package it.paa.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public abstract class TipoClassificazione extends PanacheEntity {

    @Column(name = "codice")
    private String codice;

    @Column(name = "descrizione")
    private String descrizione;


    @Transient
    public abstract String getCategoria();

    @Transient
    public abstract String getSiglaCategoria();

    @Transient
    public String getNomeCompleto() {
        return getCodice() + " - " + getDescrizione();
    }
}
