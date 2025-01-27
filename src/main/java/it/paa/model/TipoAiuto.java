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
    private String codice;

    @Column(name = "codice_2007_2013")
    private String codice20072013;

}