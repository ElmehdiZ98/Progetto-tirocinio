package it.paa.api;

import io.quarkus.security.Authenticated;
import it.paa.ExceptionProjectConstant;
import it.paa.dto.CorsoDTO;
import it.paa.dto.DettaglioCorsoDTO;

import it.paa.dto.ModuloDTO;
import it.paa.model.Modulo;
import it.paa.model.TipoMetodologia;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import it.paa.model.Corso;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;



/**
 * Classe resource per la gestione
 *  delle API REST relative ai corsi.
 * Fornisce endpoint per ottenere dettagli
 *  sui corsi e la lista dei corsi attivi.
 */
@Path("/courses")
@ApplicationScoped
@Tag(name = "Corso")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CorsoResource {


    /**
     * Gestisce una richiesta GET per ottenere
     * la lista dei corsi attivi presenti nel sistema.
     *  API Lista Corsi
     */
    @GET
    @Path("/all")
    @Operation(summary = "Recupera la lista dei corsi ", description = "Recupera tutti i corsi .")
    @APIResponse(responseCode = "200", description = "Lista dei corsi recuperata con successo", content = @Content(mediaType = MediaType.APPLICATION_JSON))
    @APIResponse(responseCode = "404", description = "Nessun corso trovato")
    public List<CorsoDTO> getCorsi(
            @Parameter(description = "codiceCorso")
            @QueryParam("codiceCorso") String codiceCorso
    ) {
        //se codice corso è diverso dal null allora fai il filtro anche per codice corso altrimenti fai la find all come sotto
        if (codiceCorso != null && !codiceCorso.isEmpty()) {
            return Corso.find("codiceCorso", codiceCorso).stream()
                    .map(corso -> Corso.toDTO((Corso) corso))
                    .collect(Collectors.toList());
        }

        return Corso.listAll().stream()
                .map(corso -> Corso.toDTO((Corso) corso))
                .collect(Collectors.toList());
    }

    /**
     * API Dettaglio corsi
     */
    @GET
    @Operation(summary = "Recupera i dettagli del corso ", description = "Recupera tutti i dettagli di un corso .")
    @APIResponse(responseCode = "200", description = "Dettagli del corso recuperati con successo", content = @Content(mediaType = MediaType.APPLICATION_JSON))
    @Path("/{uniqueCode}")
    public Response getCorsoDetails(@PathParam("uniqueCode") String uniqueCode) {
        Corso corso = Corso.find("codiceCorso", uniqueCode).firstResult();
        if (corso == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(ExceptionProjectConstant.Corso_CODE_NOT_FOUND)
                    .build();
        }

        DettaglioCorsoDTO CorsoDetails = mapCorsoToDTO(corso);
        return Response.ok(CorsoDetails).build();
    }

    private DettaglioCorsoDTO mapCorsoToDTO(Corso Corso) {
        DettaglioCorsoDTO dto = new DettaglioCorsoDTO();

        dto.setCodiceCorso(Corso.getCodiceCorso());
        dto.setTitoloCorso(Corso.getTitoloCorso());
        dto.setMacroCategoriaModalitaFormativa(
                Corso.getTipoModalitaFormativa().getClasseModalitaFormativa()
                        .getMacroCategoriaModalitaFormativa().getCodice()
        );
        dto.setClasseModalitaFormativa(Corso.getTipoModalitaFormativa().getClasseModalitaFormativa().getCodice());
        dto.setTipoModalitaFormativa(Corso.getTipoModalitaFormativa().getCodice());
        dto.setTipoContenutoFormativo(Corso.getTipoContenutoFormativo().getCodice());
        dto.setDataInizio(Corso.getDataInizio());
        dto.setDataFine(Corso.getDataFine());
        dto.setTipoCriterioSelezione(Corso.getTipoCriterioSelezione().getCodice());
        dto.setTipoAttestazioneFinale(Corso.getTipoAttestazioneFinale().getCodice());
        dto.setQualifica(Corso.getQualifica() != null ? Corso.getQualifica().getCodice() : "N/A");
        dto.setOreAulaFad(Corso.getOreAulaFad());
        dto.setOreStageTirocinio(Corso.getOreStageTirocinio());
        dto.setOreLaboratorio(Corso.getOreLaboratorio());
        dto.setDurata(Corso.getDurata());
        dto.setNumeroDocentiFormatoriTutor(Corso.getNumeroDocentiFormatoriTutor());
        dto.setMaxAllievi(Corso.getMaxAllievi());

        dto.setModuli(Corso.modulo.stream()
                .map(this::mapModuloToDTO)
                .collect(Collectors.toList()));

        return dto;
    }

    private ModuloDTO mapModuloToDTO(Modulo modulo) {
        ModuloDTO moduloDTO = new ModuloDTO();

        moduloDTO.setTitoloModulo(modulo.getTitolo());
        moduloDTO.setNumeroOre(modulo.getNumeroOre());
        moduloDTO.setDataInizioModulo(modulo.getDataInizio());
        moduloDTO.setDataFineModulo(modulo.getDataFine());

        List<TipoMetodologia> metodologie = new ArrayList<>();
        if (modulo.getMetodologia() != null) {
            metodologie.addAll(modulo.getMetodologia());
        }
        moduloDTO.setMetodologie(metodologie);

        return moduloDTO;
    }
}
