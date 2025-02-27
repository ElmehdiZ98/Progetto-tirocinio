package it.paa.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "tipo_aiuto")
@NamedQuery(name = "TipoAiuto.findAll", query = "SELECT t FROM TipoAiuto t")
public class TipoAiuto extends PanacheEntity {

    @Column(name = "codice")
    public String codice;

    @Column(name = "descrizione")
    public String descrizione;

    @Column(name = "codice_2007_2013")
    public String codice20072013;

}