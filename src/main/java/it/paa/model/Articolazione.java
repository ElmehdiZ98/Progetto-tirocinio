package it.paa.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.security.Authenticated;
import it.paa.dto.ListaArticolazioneDTO;
import it.paa.interfaces.NestedSetsTreeNode;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.hibernate.envers.RelationTargetAuditMode;

import javax.swing.tree.TreeNode;
import java.math.BigDecimal;
import java.util.Enumeration;


@Entity
@Getter
@Setter
@Table(name = "articolazione")
@NamedQuery(name = "Articolazione.findAll", query = "SELECT a FROM Articolazione a")
@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
@AuditTable(value = "history_articolazione")
@Inheritance(strategy = InheritanceType.JOINED)
public class Articolazione extends PanacheEntity implements NestedSetsTreeNode {

    @Column(name = "codice")
    @NotNull
    @Size(max = 255)
    public String codice;    //CCI


    @Column(name = "dotazione_finanziaria", precision = 15, scale = 2)
    @NotNull
    @Min(value = 0)
    public BigDecimal dotazioneFinanziaria;      //Dotazione Finanziaria

    @Column(name = "denominazione")
    @Size(min = 5, max = 255)
    @NotNull(message = "La denominazione è obbligatoria")
    public String denominazione;    //Denominazione articolazione programma

    @Column(name = "descrizione")
    @Basic(fetch = FetchType.EAGER)
    public String descrizione;     //Descrizione

    @ManyToOne(targetEntity = Articolazione.class)
    @JoinColumn(name = "top_level")
    public NestedSetsTreeNode topLevel;       //Denominazione programma

    @OneToOne(cascade = CascadeType.REMOVE,
            orphanRemoval = true, mappedBy = "articolazione")
    @NotAudited
    public ClassificazioneArticolazione classificazioneArticolazione;


    @Transient
    public Programma getProgramma() {
        return (Programma) topLevel;
    }

    @Transient
    public String getNomeCompleto() {           //Titolo del programma
        return codice.concat(" - ")
                .concat(denominazione);
    }


    @Override
    public int getLeft() {
        return 0;
    }

    @Override
    public void setLeft(int left) {

    }

    @Override
    public TreeNode getChildAt(int childIndex) {
        return null;
    }

    @Override
    public int getChildCount() {
        return 0;
    }

    @Override
    public TreeNode getParent() {
        return null;
    }

    @Override
    public int getIndex(TreeNode node) {
        return 0;
    }

    @Override
    public boolean getAllowsChildren() {
        return false;
    }

    @Override
    public boolean isLeaf() {
        return false;
    }

    @Override
    public Enumeration<? extends TreeNode> children() {
        return null;
    }


    //@Authenticated
    public static ListaArticolazioneDTO getProgrammaDetails(String codice)  {
        Articolazione articolazione = Articolazione.find("codice", codice).firstResult();

        if (articolazione == null) {
            throw new EntityNotFoundException("Articolazione con ID " + codice + " non trovata.");
        }
        if (!(articolazione instanceof Programma programma)) {
            throw new IllegalArgumentException("L'Articolazione con ID " + codice + " non è un Programma.");
        }

        ListaArticolazioneDTO dto = new ListaArticolazioneDTO();
        dto.setCodice(programma.getCodice());
        dto.setDotazioneFinanziaria(programma.getDotazioneFinanziaria());
        dto.setTitolo(programma.getNomeCompleto());
        dto.setTipoProgramma(programma.getTipoProgramma());
        dto.setTipoFondo(programma.getTipoFondo());

        return dto;
    }


}

