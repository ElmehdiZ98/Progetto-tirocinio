package it.paa.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NamedQuery;

@Entity
@Getter
@Setter
@Table(name = "progetto_complesso")
@XmlRootElement
@NamedQuery(name = "ProgettoComplesso.findAll",query = "SELECT o FROM ProgettoComplesso o")
public class ProgettoComplesso extends PanacheEntity {


    @ManyToOne
    @JoinColumn(name = "anagrafica_id")
    public Anagrafica soggettoProponente;

    @Column(name="descrizione")
    public String descrizione;



}
