package it.paa.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "tipo_soggetto_diritto")
@XmlRootElement
@NamedQuery(name = "TipoSoggettoDiritto.findAll", query = "SELECT t FROM TipoSoggettoDiritto t")
public class TipoSoggettoDiritto extends PanacheEntity {

    @Column(name = "descrizione")
    public String descrizione;
}