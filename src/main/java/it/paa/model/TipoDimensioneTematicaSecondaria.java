package it.paa.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name = "tipo_dim_tem_sec")
@NamedQuery(name = "TipoDimensioneTematicaSecondaria.findAll", query = "SELECT t FROM TipoDimensioneTematicaSecondaria t")
public class TipoDimensioneTematicaSecondaria extends TipoClassificazione {

    @Column(name = "codice")
    public String codice;

    @Column(name = "descrizione")
    public String descrizione;

    @Override
    public String getCategoria() {
        return "";
    }

    @Override
    public String getSiglaCategoria() {
        return "";
    }
}
