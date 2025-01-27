package it.paa.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;
import jakarta.persistence.*;


import lombok.Setter;
import lombok.Getter;
import org.hibernate.type.YesNoConverter;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "anagrafica")
public class Anagrafica extends PanacheEntity {

    @Size(max = 50)
    @Pattern(regexp = "[a-zA-Zàèìòù' \t\r\n\f]+\\.?", message = "Formato Nome non valido")
    public String nome;

    @Size(max = 50)
    @Pattern(regexp = "[a-zA-Zàèìòù' \t\r\n\f]+\\.?", message = "Formato Cognome non valido")
    public String cognome;


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

//    @ManyToOne
//    @JoinColumn(name = "tipo_genere")
//    public TipoGenere tipoGenere;
//
    @ManyToOne
    @JoinColumn(name = "tipo_soggetto_diritto")
    @NotNull
    public TipoSoggettoDiritto tipoSoggettoDiritto;


    @Column(name = "codice_IPA")
    @Size(max = 255)
    private String codiceIPA;         //codiceIPA
//
//    @ManyToOne
//    @JoinColumn(name = "tipo_dimensione_impresa_id")
//    public TipoDimensioneImpresa tipoDimensioneImpresa;


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


//    @ManyToOne
//    @JoinColumn(name = "luogo_nascita_rappr_legale")
//    public Comune luogoNascitaRappresentanteLegale;

//    @Column(name = "codice_fiscale_rappr_legale")
//    @Size(min = 16, max = 16)
//    @CodiceFiscale
//    public String codiceFiscaleRappresentanteLegale;

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

//    @ManyToOne
//    @JoinColumn(name = "tipo_cittadinanza_id")
//    public TipoCittadinanza tipoCittadinanza;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "documento_riconoscimento")
//    public AlfrescoDocument documentoRiconoscimento;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "documento_pdf_firmato")
//    public AlfrescoDocument documentoPdfFirmato;

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

//    @ManyToOne
//    @JoinColumn(name = "tipo_documento_identita_id")
//    public TipoDocumentoIdentita tipoDocumentoIdentita;

    public boolean isCfEstero() {
        return isCfEstero;
    }



}