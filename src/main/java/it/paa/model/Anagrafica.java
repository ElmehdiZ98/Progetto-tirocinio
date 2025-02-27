package it.paa.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;
import jakarta.persistence.*;


import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Setter;
import lombok.Getter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;
import org.hibernate.type.YesNoConverter;
import java.util.Date;

@Entity
@Getter
@Setter
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Table(name = "anagrafica")
@NamedQuery(name = "Anagrafica.findAll", query = "SELECT a FROM Anagrafica a")
@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
@AuditTable(value = "history_anagrafica")
public class Anagrafica extends PanacheEntity {

    @Size(max = 50)
    @Pattern(regexp = "[a-zA-Zàèìòù' \t\r\n\f]+\\.?", message = "Formato Nome non valido")
    @Column(name = "nome")
    public String nome;          //nome

    @Size(max = 50)
    @Pattern(regexp = "[a-zA-Zàèìòù' \t\r\n\f]+\\.?", message = "Formato Cognome non valido")
    @Column(name = "cognome")
    public String cognome;        //cognome


    @Column(name = "email", unique = true)
    public String email;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_nascita")
    public Date dataNascita;

    @Size(max = 50)
    public String qualifica;

    @Size(min = 9, max = 12)
    @Digits(fraction = 0, integer = 12, message = "La lunghezza del campo telefono deve essere compresa tra 9 e 12 caratteri")
    public String telefono;

    @Size(min = 9, max = 12)
    @Digits(fraction = 0, integer = 12, message = "La lunghezza del campo cellulare deve essere compresa tra 9 e 12 caratteri")
    @Column(name = "cellulare")
    public String cellulare;

    @Size(min = 9, max = 12)
    @Digits(fraction = 0, integer = 12, message = "La lunghezza del campo fax deve essere compresa tra 9 e 12 caratteri")
    public String fax;

    @Column(name = "codice_fiscale")
    public String codiceFiscale;

    @Column(name = "codice_fiscale_giuridico")
    @Size(min = 11, max = 11)
    public String codiceFiscaleGiuridico;

    @Column(name = "partita_iva")
    @Size(min = 11, max = 11)
    public String partitaIva;

    @Column(name = "partita_iva_estera")
    @Size(min = 1, max = 16)
    public String partitaIvaEstera;

    @Column(name = "cf_piva")
    public String cfPiva;       //CFP.iva

    @Size(max = 200)
    public String denominazione;

    @Size(max = 200)
    @Column(name = "nome_completo")
    public String nomeCompleto;

    @ManyToOne
    @JoinColumn(name = "tipo_forma_giuridica_id")
    public TipoFormaGiuridica tipoFormaGiuridica;        //Tipo forma giudiziaria


    @ManyToOne
    @JoinColumn(name = "tipo_codice_ateco_id")
    public TipoCodiceAteco tipoCodiceAteco;

    @ManyToOne
    @JoinColumn(name = "tipo_soggetto_diritto")
    @NotNull
    public TipoSoggettoDiritto tipoSoggettoDiritto;


    @Column(name = "codice_IPA")
    @Size(max = 255)
    private String codiceIPA;         //codiceIPA


    @Column(name = "nome_rappr_legale")
    @Size(max = 50)
    @Pattern(regexp = "[a-zA-Zàèìòù' \t\r\n\f]+\\.?", message = "Formato Nome non valido")
    public String nomeRappresentanteLegale;

    @Column(name = "cognome_rappr_legale")
    @Size(max = 50)
    @Pattern(regexp = "[a-zA-Zàèìòù' \t\r\n\f]+\\.?", message = "Formato Cognome non valido")
    public String cognomeRappresentanteLegale;

    @Column(name = "email_rappr_legale")
    public String emailRappresentanteLegale;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_nascita_rappr_legale")
    @Past
    public Date dataNascitaRappresentanteLegale;

    @Column(name = "telefono_rappr_legale")
    @Size(min = 9, max = 12)
    @Digits(fraction = 0, integer = 12, message = "La lunghezza del campo telefono rapp legale deve essere compresa tra 9 e 12 caratteri")
    public String telefonoRappresentanteLegale;

    @Column(name = "is_cf_estero")
    @Convert(converter = YesNoConverter.class)
    public boolean isCfEstero = false;


    @Column(name = "privacy_policy", length = 1)
    @Convert(converter = YesNoConverter.class)
    public Boolean privacyPolicy = Boolean.FALSE;


    @Column(name = "documento_numero")
    public String documentoNumero;

    @Column(name = "documento_altro")
    public String documentoAltro;

    @Column(name = "documento_rilasciato_da")
    public String documentoRilasciatoDa;

    @Column(name = "documento_data_rilascio")
    public Date documentoDataRilascio;

    @Column(name = "documento_data_validita")
    public Date documentoDataValidita;

    public boolean isCfEstero() {
        return isCfEstero;
    }



}