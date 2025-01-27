package it.paa.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "tipo_forme_finanziamento")
@NamedQuery(name = "TipoFormeFinanziamento.findAll", query = "SELECT t FROM TipoFormeFinanziamento t")
public class TipoFormeFinanziamento extends TipoClassificazione {

    @Column(name = "codice")
    public String codice;

    @Column(name = "descrizione")
    public String descrizione;

    @Override
    public String getCategoria() {return "";}

    @Override
    public String getSiglaCategoria() {
        return "";
    }
}
