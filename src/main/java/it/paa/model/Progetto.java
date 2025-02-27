package it.paa.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import it.paa.dto.AvvisiBandiDTO;
import it.paa.dto.DettaglioProgettoDTO;
import it.paa.dto.ProgettoDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Entity
@Getter
@Setter
@Table(name = "progetto")
@NamedQuery(name = "Progetto.findAll", query = "SELECT p FROM Progetto p ")
@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
public class Progetto extends PanacheEntity {

    @Column(name = "titolo")
    @Size(max = 500)
    @NotNull(message = "il titolo è obbligatorio")
    public String titolo;         //titolo

    @Column(name = "sintesi_progetto")
    @Size(max = 1300)
    @NotNull(message = "la sintesi è obbligatoria")
    public String sintesiProgetto;   //sintesi

    @Column(name = "costo", precision = 15, scale = 2)
    @Min(0)
    public BigDecimal costo = BigDecimal.ZERO;   //costo

    @Column(name = "cup_definitivo")
    @Pattern(regexp = "[A-Z0-9]{14}[1-9]",
            message = "CUP definitivo non conforme (primi 14 caratteri alfanumerici " +
            "ultimo carattere non 0)")
    public String cupDefinitivo;         //CUP defenitivo

    @Column(name = "codice_locale_progetto")
    @Size(max = 255)
    public String codiceLocaleProgetto;    //codice Locale Progetto

    @Column(name = "cod_proc_att_orig")
    public String codiceProceduraAttivazioneOriginaria;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "procedura_attivazione_id")
    public ProceduraAttivazione proceduraAttivazione;

    @ManyToOne
    @JoinColumn(name = "tipo_investimento_id")
    @NotNull
    public TipoInvestimento tipoInvestimento;

    @ManyToOne
    @JoinColumn(name = "tipo_aiuto_id")
    @NotNull
    public TipoAiuto tipoAiuto;

    @ManyToOne
    @JoinColumn(name = "current_state")
    public StepWorkflow currentState;

    @OneToMany(mappedBy = "progetto")
    public List<ProgettoProgramma> progettoProgrammaList = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "localizzazione_progetto",
            joinColumns = @JoinColumn(name = "progetto_id"),
            inverseJoinColumns = @JoinColumn(name = "localizzazione_id")
    )
    public List<Localizzazione> localizzazione;


    @Transient
    public boolean isPropostaProgetto() {
        return currentState.getMarker() != null && currentState.getMarker()
                .equals("PROPOSTA_PROGETTUALE");
    }

}
