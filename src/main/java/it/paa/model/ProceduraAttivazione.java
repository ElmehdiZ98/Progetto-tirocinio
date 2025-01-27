package it.paa.model;



import io.quarkus.hibernate.orm.panache.PanacheEntity;
import it.paa.dto.AvvisiBandiDTO;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;
import org.hibernate.type.YesNoConverter;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Classe per entità procedura_attivazione
 */
@Entity
@Setter
@Getter
@Table(name = "procedura_attivazione")
@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
@AuditTable("history_procedura_attivazione")
public class ProceduraAttivazione extends PanacheEntity {


    @Column(name = "aiuto", length = 1)
    @Convert(converter = YesNoConverter.class)
    public Boolean aiuto = Boolean.FALSE;      //Aiuto: si/no

    @Column(name = "responsabile_procedimento")
    public String responsabileProcedimento;    //Responsabile procedimento

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "start_datetime", length = 19)
    public Date startDatetime;              //Data di avvio

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "end_datetime", length = 19)
    public Date endDatetime;                  //Data di fine

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "atto_id")
    @Valid
    @Audited
    public AttoProceduraAttivazione atto;

    @Column(name = "codice")
    @Length(max = 30)
    public String codiceProceduraAttivazione;     //Codice IGRUE

    @Column(name = "descrizione")
    @Length(max = 255)
    @NotNull
    public String descrizione;                //Codice PRATT/denominazione procedura

    @ManyToOne
    @JoinColumn(name = "id_tipo_procedura_attivazione")
    public TipoProceduraAttivazione tipoProceduraAttivazione;    //Tipo di procedura

    @Column(name = "importo_totale", precision = 15, scale = 2)
    @Min(0)
    public BigDecimal importoTotale = BigDecimal.ZERO;   //Importo della procedura

    @ManyToOne
    @JoinColumn(name = "current_state")
    public StepWorkflow currentState;       //Stato

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "art_proc_att",
            joinColumns = @JoinColumn(name = "procedura_attivazione_id"),
            inverseJoinColumns = @JoinColumn(name = "articolazione_id")
    )
    public List<Articolazione> articolazione;

    public static List<AvvisiBandiDTO> mapArticolazioniToDto(ProceduraAttivazione procedura) {
        return procedura.articolazione.stream()
                .map(articolazione -> new AvvisiBandiDTO(
                        procedura.codiceProceduraAttivazione,
                        procedura.descrizione,
                        procedura.tipoProceduraAttivazione,
                        procedura.importoTotale,
                        procedura.currentState ,
                        articolazione.codice,
                        articolazione.denominazione,
                        articolazione.getNomeCompleto()
                ))
                .collect(Collectors.toList());
    }



}

