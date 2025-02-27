package it.paa.model;


import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@XmlRootElement
@Table(name = "soggetto_correlato")
@NamedQuery(name = "SoggettoCorrelato.findAll", query = "SELECT sc FROM SoggettoCorrelato sc")
public class SoggettoCorrelato extends PanacheEntity {

    @ManyToOne
    @JoinColumn(name = "progetto_id", nullable = false)
    public Progetto progetto;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "anagrafica_id")
    public Anagrafica anagrafica;


}

