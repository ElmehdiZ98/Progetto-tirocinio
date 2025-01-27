package it.paa.model;


import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.xml.bind.annotation.XmlRootElement;


@Entity
@XmlRootElement
@Table(name = "tipo_class_proc_att")
@NamedQuery(name = "TipoClassificazioneProcAtt.findAll", query = "SELECT t FROM TipoClassificazioneProcAtt t")
public class TipoClassificazioneProcAtt extends PanacheEntity {

    @Column(name = "descrizione")
    public String descrizione;
}
