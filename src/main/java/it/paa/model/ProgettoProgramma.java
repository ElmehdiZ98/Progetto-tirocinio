package it.paa.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@XmlRootElement
@Table(name = "progetto_programma")
@NamedQuery(name = "ProgettoProgramma.findAll", query = "SELECT op FROM ProgettoProgramma op")
@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
@AuditTable(value = "history_progetto_programma")
public class ProgettoProgramma extends PanacheEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "progetto_id")
    @NotNull(message = "Il progetto è obbligatorio")
    public Progetto progetto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "procedura_attivazione_id")
    public ProceduraAttivazione proceduraAttivazione;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "articolazione_id")
    public Articolazione articolazione;

    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinColumn(name = "class_prog_progr_id")
    @Audited
    public ClassificazioneProgettoProgramma classificazioneProgettoProgramma;


    public static List<ClassificazioneProgettoProgramma> findClassificazioniByProgetto(Progetto progetto) {
        if (progetto == null) {
            throw new IllegalArgumentException("Il progetto non può essere null");
        }
        // Recupera tutti i ProgettoProgramma associati al progetto
        List<ProgettoProgramma> progettoProgrammi = ProgettoProgramma.find("progetto", progetto).list();

        List<ClassificazioneProgettoProgramma> classificazioni = new ArrayList<>();
        for (ProgettoProgramma progettoProgramma : progettoProgrammi) {
            if (progettoProgramma.classificazioneProgettoProgramma != null) {
                classificazioni.add(progettoProgramma.classificazioneProgettoProgramma);
            }
        }

        return classificazioni;
    }


}
