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
@Table(name = "settore_contenuto_formativo")
@NamedQuery(name = "SettoreContenutoFormativo.findAll", query = "SELECT t FROM SettoreContenutoFormativo t")
public class SettoreContenutoFormativo extends PanacheEntity {

    @Column(name = "codice")
    private String codice;

    @Column
    public String descrizione;

}

