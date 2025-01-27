package it.paa.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.type.YesNoConverter;

@Entity
@Getter
@Setter
@XmlRootElement
@Table(name = "tipo_atto")
@NamedQuery(name = "TipoAtto.findAll", query = "SELECT t FROM TipoAtto t")
public class TipoAtto extends PanacheEntity {

    @Column(name = "versionamento")
    @Convert(converter = YesNoConverter.class)
    private Boolean versionamento = Boolean.FALSE;


}
