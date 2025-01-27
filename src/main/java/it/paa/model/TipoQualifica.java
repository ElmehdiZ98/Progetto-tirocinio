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
@Table(name = "tipo_qualifica")
@NamedQuery(name = "TipoQualifica.findAll", query = "SELECT t FROM TipoQualifica t")
public class TipoQualifica extends PanacheEntity {

    @Column(name = "codice")
    public String codice;

    @Column(name = "codice_amministrazione")
    public String codiceAmministrazione;



}
