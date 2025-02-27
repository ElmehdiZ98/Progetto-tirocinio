package it.paa.model;

import jakarta.persistence.*;


import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;



@Getter
@Setter
@Entity
@Table(name = "programma")
@NamedQuery(name = "Programma.findAll", query = "SELECT p FROM Programma p ")
@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
@AuditTable(value = "history_programma")
public class Programma extends Articolazione {


    @ManyToOne
    @JoinColumn(name = "tipo_programma")
    public TipoProgramma tipoProgramma;     //TipoProgramma

    @Column(name = "tipo_fondo", length = 100)
    public String tipoFondo;           //TipoFondo

    @OneToOne
    @JoinColumn(name = "atto")
    @Valid
    public Atto atto;              // Atto

















    //    @Column(name = "checklist_abilitata")
//    @Convert(converter = YesNoConverter.class)
//    public Boolean checklistAbilitata = Boolean.FALSE;
//
//    @Column(name = "coerente")
//    @Convert(converter = YesNoConverter.class)
//    public Boolean coerente=Boolean.FALSE;




//
//    @Column(name = "convalidato")
//    @Convert(converter = YesNoConverter.class)
//    public Boolean convalidato = Boolean.FALSE;;
//
//    @Column(name = "note")
//    @Length(max = 65535)
//    @Lob
//    @Basic(fetch = FetchType.EAGER)
//    public String note;
//

//
//    @OneToOne
//    @JoinColumn(name = "atto_comunitario")
//    @Valid
//    public Atto attoComunitario;
//
//









}
