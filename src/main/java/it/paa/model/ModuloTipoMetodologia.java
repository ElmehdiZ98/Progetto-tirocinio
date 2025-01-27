package it.paa.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@XmlRootElement
@Table(name = "modulo_tipo_metodologia")
@NamedQuery(name = "ModuloTipoMetodologia.findAll", query = "SELECT m FROM ModuloTipoMetodologia m")
public class ModuloTipoMetodologia extends PanacheEntity {

    @ManyToOne
    @JoinColumn(name = "modulo_id", nullable = false)
    @NotNull
    public Modulo modulo;

    @ManyToOne
    @JoinColumn(name = "tipo_metodologia_id")
    @NotNull
    public TipoMetodologia tipoMetodologia;


}
