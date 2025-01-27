package it.paa.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import it.paa.enums.CategoriaTipoCodiceAteco;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "tipo_codice_ateco")
@NamedQuery(name = "TipoCodiceAteco.findAll", query = "SELECT t FROM TipoCodiceAteco t")
public class TipoCodiceAteco extends PanacheEntity {

    @Column(name = "codice")
    public String codice;

    @Column(name = "parent_id")
    public Long parentId;

    @Column(name = "categoria")
    @Enumerated(EnumType.STRING)
    @NotNull
    public CategoriaTipoCodiceAteco categoria;

    @Column(name = "anno")
    public Integer anno;



}
