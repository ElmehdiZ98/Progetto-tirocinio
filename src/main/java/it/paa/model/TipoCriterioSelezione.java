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
@Table(name = "tipo_criterio_selezione")
@NamedQuery(name = "TipoCriterioSelezione.findAll", query = "SELECT t FROM TipoCriterioSelezione t")
public class TipoCriterioSelezione extends PanacheEntity {

    @Column(name = "codice")
    public String codice;

    @Column(name = "descrizione")
    public String descrizione;


}