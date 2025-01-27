package it.paa.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "step_workflow")
public class StepWorkflow extends PanacheEntity {


    @Column(name = "nome")
    public String nome;

    @Column(name = "codice")
    public String codice;

    @Column(name = "marker")
    private String marker;

}
