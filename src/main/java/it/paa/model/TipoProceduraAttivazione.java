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

@Setter
@Getter
@Entity
@Table(name = "tipo_procedura_attivazione")
@NamedQuery(name = "TipoProceduraAttivazione.findAll", query = "SELECT t FROM TipoProceduraAttivazione t")
public class TipoProceduraAttivazione extends PanacheEntity {

    @Column(name = "codice")
    @Length(max = 2)
    @NotNull
    public String codice;

    @Column(name = "descrizione")
    public String descrizione;

}