package it.paa.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.type.YesNoConverter;

import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name = "tipo_cup")
@NamedQuery(name = "TipoCup.findAll", query = "SELECT t FROM TipoCup t")
public class TipoCup extends PanacheEntity implements Serializable {

    @Column(name = "codice")
    public String codice;

    @Column(name = "deprecated")
    @Convert(converter = YesNoConverter.class)
    public Boolean deprecated = false;


}