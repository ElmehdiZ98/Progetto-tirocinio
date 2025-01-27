package it.paa.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Entity
@Getter
@Setter
@XmlRootElement
@Table(name = "tipo_atto_procedura")
@NamedQuery(name = "TipoAttoProcedura.findAll", query = "SELECT t FROM TipoAttoProcedura t")
public class TipoAttoProcedura extends PanacheEntity {

    @Column(name = "codice")
    @Length(max = 2)
    @NotNull
    private String codice;

    @Column(name = "descrizione")
    private String descrizione;


}