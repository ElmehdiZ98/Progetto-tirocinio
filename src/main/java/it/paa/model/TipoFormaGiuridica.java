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
@Table(name = "tipo_forma_giuridica")
@NamedQuery(name = "TipoFormaGiuridica.findAll", query = "SELECT t FROM TipoFormaGiuridica t")
public class TipoFormaGiuridica extends PanacheEntity {

    @Column(name = "codice")
    public String codice;

    @Column(name = "divisione")
    public String divisione;

    @Column(name = "sezione")
    public String sezione;

    @Column(name = "descrizione")
    public String descrizione;

}
