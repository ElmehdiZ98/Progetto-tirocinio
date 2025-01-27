package it.paa.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@XmlRootElement
@Table(name = "tipo_programma")
@NamedQuery(name = "TipoProgramma.findAll", query = "SELECT t FROM TipoProgramma t")
public class TipoProgramma extends PanacheEntity {

    @NotNull
    public String descrizione;
}
