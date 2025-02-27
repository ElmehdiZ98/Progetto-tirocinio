package it.paa.api;

import io.quarkus.security.Authenticated;
import it.paa.ExceptionProjectConstant;
import it.paa.dto.DettaglioProgettoDTO;
import it.paa.dto.SoggettoDTO;
import it.paa.model.*;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import java.util.ArrayList;
import java.util.List;



import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/projects")
@ApplicationScoped
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class ProgettoResource {


    @GET
    @Path("/{uniqueCode}/verify-project-code")
    @Operation(summary = "Verifica l'esistenza di un codice locale progetto", description = "Controlla se il codice locale progetto fornito esiste nel sistema MOSEM")
    @APIResponse(responseCode = "200", description = "Codice locale progetto trovato", content = @Content(mediaType = MediaType.APPLICATION_JSON))
    @APIResponse(responseCode = "404", description = "Codice locale progetto non trovato")
    public Response VerifyCodeProject(@Parameter(description = "Project code local", required = true)
                                      @PathParam("uniqueCode") String uniqueCode) {


        Progetto progetto = Progetto.find("codiceLocaleProgetto", uniqueCode).firstResult();
        if (progetto == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(ExceptionProjectConstant.PROJECT_CODE_NOT_FOUND_MESSAGE)
                    .build();
        }
        return Response.ok(ExceptionProjectConstant.PROJECT_CODE_VALID_MESSAGE).build();
    }

    /**
     * API RESTfull dettaglio progetto
     */

    @GET
    @Path("/{projectId}/details")
    @Operation(summary = "Recupera dettagli sul progetto", description = "Restituisce dettagli di un progetto presenti nel sistema")
    @APIResponse(responseCode = "200", description = "Codice locale progetto trovato", content = @Content(mediaType = MediaType.APPLICATION_JSON))
    public Response DettaglioProgetto(@Parameter(description = "id", required = true)
                                          @PathParam("projectId") Long id) {
        Progetto progetto = Progetto.findById(id);
        if (progetto == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(ExceptionProjectConstant.PROJECT_CODE_NOT_FOUND_MESSAGE)
                    .build();
        }
        DettaglioProgettoDTO dettaglioProgettoDTO =  getDettaglioProgettoDTO(progetto);
        return Response.ok(dettaglioProgettoDTO).build();
    }
    @GET
    @Path("/{projectId}")
    @Operation(summary = "Trova un progetto per ID", description = "Restituisce i dettagli di un progetto dato il suo ID")
    @APIResponse(responseCode = "200", description = "Progetto trovato", content = @Content(mediaType = MediaType.APPLICATION_JSON))
    @APIResponse(responseCode = "404", description = "Progetto non trovato")
    public Response getProgettoById(@Parameter(description = "ID del progetto", required = true)
                             @PathParam("projectId") Long id) {
        Progetto progetto = Progetto.findById(id);
        if (progetto == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(ExceptionProjectConstant.PROJECT_NOT_FOUND_MESSAGE)
                    .build();
        }
        return Response.ok(progetto).build();
    }


    @GET
    @Path("/all")
    @Operation(summary = "Recupera tutti i progetti", description = "Restituisce la lista di tutti i progetti presenti nel sistema")
    @APIResponse(responseCode = "200", description = "Lista dei progetti", content = @Content(mediaType = MediaType.APPLICATION_JSON))
    public Response getAll() {
        List<Progetto> progetti = Progetto.listAll();
        return Response.ok(progetti).build();
    }


    private static DettaglioProgettoDTO getDettaglioProgettoDTO(Progetto progetto) {

        DettaglioProgettoDTO dettaglioProgettoDTO = new DettaglioProgettoDTO();

        dettaglioProgettoDTO.setTitolo(progetto.getTitolo());
        dettaglioProgettoDTO.setSintesiProgetto(progetto.getSintesiProgetto());
        dettaglioProgettoDTO.setCupDefinitivo(progetto.getCupDefinitivo());
        dettaglioProgettoDTO.setCosto(progetto.getCosto());
        dettaglioProgettoDTO.setLocalizzazioneList(progetto.getLocalizzazione());

        ProceduraAttivazione attivazione = progetto.getProceduraAttivazione();
        dettaglioProgettoDTO.setCodiceProceduraAttivazione(attivazione.getCodiceProceduraAttivazione());
        dettaglioProgettoDTO.setStartDatetime(attivazione.getStartDatetime());
        dettaglioProgettoDTO.setImportoTotale(attivazione.getImportoTotale());

        AttoProceduraAttivazione attoProceduraAttivazione = attivazione.getAtto();
        dettaglioProgettoDTO.setAtto(attoProceduraAttivazione);

        TipoInvestimento tipoInvestimento = progetto.getTipoInvestimento();
        dettaglioProgettoDTO.setTipoNaturaCup(tipoInvestimento.getTipoNaturaCup());
        dettaglioProgettoDTO.setTipoCup(tipoInvestimento.getTipoCup());

        List<SoggettoCorrelato> soggettiCorrelati = SoggettoCorrelato.find("progetto", progetto).list();
        List<SoggettoDTO> soggettiDTO = new ArrayList<>();

        for (SoggettoCorrelato soggettoCorrelato : soggettiCorrelati) {
            if (soggettoCorrelato.anagrafica != null) {
                SoggettoDTO soggettoDTO = getSoggettoDTO(soggettoCorrelato);
                soggettiDTO.add(soggettoDTO);
            }
        }

        dettaglioProgettoDTO.setSoggetti(soggettiDTO);

        List<ClassificazioneProgettoProgramma> classificazioni = ProgettoProgramma.findClassificazioniByProgetto(progetto);
        for (ClassificazioneProgettoProgramma classificazione : classificazioni) {
            if (classificazione.getTipoCampoIntervento() != null) {
                dettaglioProgettoDTO.setTipoCampoInterventoList(classificazione.getTipoCampoIntervento());
            }
            if (classificazione.getTipoFormeFinanziamento() != null) {
                dettaglioProgettoDTO.setTipoFormeFinanziamentoList(classificazione.getTipoFormeFinanziamento());
            }
            if (classificazione.getTipoDimensioneTematicaSecondaria() != null) {
                dettaglioProgettoDTO.setTipoDimensioneTematicaSecondariaList(classificazione.getTipoDimensioneTematicaSecondaria());
            }
            if (classificazione.getTipoRisultatoAtteso() != null) {
                dettaglioProgettoDTO.setTipoRisultatoAttesoList(classificazione.getTipoRisultatoAtteso());
            }

        }
        // recupero del codice e la descrizione dell'articolazione
        List<ProgettoProgramma> progettoProgrammi = ProgettoProgramma.find("progetto", progetto).list();
        for (ProgettoProgramma progettoProgramma : progettoProgrammi) {
            Articolazione articolazione = progettoProgramma.getArticolazione();
            if (articolazione != null) {
                dettaglioProgettoDTO.setCodice(articolazione.getCodice());
                dettaglioProgettoDTO.setDescrizione(articolazione.getDescrizione());
            }
        }

        return dettaglioProgettoDTO;
    }

    private static SoggettoDTO getSoggettoDTO(SoggettoCorrelato soggettoCorrelato) {
        Anagrafica anagrafica = soggettoCorrelato.anagrafica;

        SoggettoDTO soggettoDTO = new SoggettoDTO();
        soggettoDTO.setDenominazione(anagrafica.getDenominazione());

        soggettoDTO.setTipoSoggettoDiritto(anagrafica.getTipoSoggettoDiritto().getDescrizione());
        soggettoDTO.setCfPiva(anagrafica.getCfPiva());
        soggettoDTO.setCodiceIPA(anagrafica.getCodiceIPA());
        soggettoDTO.setNome(anagrafica.getNome());
        soggettoDTO.setCognome(anagrafica.getCognome());

        if(anagrafica.getTipoFormaGiuridica() != null) {
            soggettoDTO.setTipoFormaGiuridica(anagrafica.getTipoFormaGiuridica().getDescrizione());
        } else {
            soggettoDTO.setTipoFormaGiuridica("Forma giuridica non disponibile");
        }
        return soggettoDTO;
    }




}