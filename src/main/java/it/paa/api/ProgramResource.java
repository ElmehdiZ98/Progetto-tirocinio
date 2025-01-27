package it.paa.api;


import it.paa.dto.DettaglioProgrammaDTO;
import it.paa.dto.ListaArticolazioneDTO;
import it.paa.exception.EntityNotFoundException;
import it.paa.model.Articolazione;
import it.paa.model.Atto;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import it.paa.model.Program;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import java.math.BigDecimal;
import java.util.List;


/**
 * Classe Resource per la gestione
 * dei programmi operativi.
 * Espone API REST per ottenere informazioni sui programmi operativi.
 */
@Path("projects/program")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Transactional
public class ProgramResource {

    /**
     * Recupera i dettagli di un programma
     * dato il suo codice identificativo.
     * API List programmi
     */

    @GET
    @Path("/{id}/details")
    @APIResponse(responseCode = "200",description = "Dettagli del programma recuperato con successo.", content = @Content(mediaType = MediaType.APPLICATION_JSON))
    @APIResponse(responseCode = "404", description = "Programma non trovato con il codice indicato")
    public Response getProgrammaDetails( @Parameter(description = "Program Id", required = true)
                                         @PathParam("id") Long id) {
        try {
            ListaArticolazioneDTO dto = Articolazione.getProgrammaDetails(id);
            return Response.ok(dto).build();
        } catch (EntityNotFoundException | IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }


     /**
      * Recupera i dettagli di un programma dato il suo codice id
      * REST API Dettaglio programma
      * */

    @GET
    @Path("/{id}")
    @APIResponse(responseCode = "200",description = "Dettagli del programma recuperato con successo.", content = @Content(mediaType = MediaType.APPLICATION_JSON))
    @APIResponse(responseCode = "404", description = "Programma non trovato ")
    public Response getProgrammaById(@Parameter(description = "Program Id", required = true)
                                         @PathParam("id") Long id) {
        Program programma = Program.findById(id);
        if (programma == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Programma non trovato").build();
        }

        // Popola il DTO con i dettagli del programma
        DettaglioProgrammaDTO programmaDTO = new DettaglioProgrammaDTO();
        programmaDTO.setCodice(programma.getCodice());
        programmaDTO.setDescrizione(programma.getDescrizione());
        programmaDTO.setTitolo(programma.getNomeCompleto());
        programmaDTO.setTipoProgramma(programma.getTipoProgramma());
        programmaDTO.setTipoFondo(programma.getTipoFondo());
        programmaDTO.setDotazioneFinanziaria(programma.getDotazioneFinanziaria());

        // Gestione dell'atto associato
        if (programma.getAtto() != null) {
            Atto atto = programma.getAtto();
            programmaDTO.setAtto(atto);
            programmaDTO.setTipoAtto(atto.getTipoAtto());
            programmaDTO.setNumeroAtto(atto.getNumeroAtto());
            programmaDTO.setDataAtto(atto.getDataAtto());
        }

        BigDecimal totaleDotazione = BigDecimal.ZERO;
        List<Articolazione> articolazioni = Articolazione.find("topLevel = ?1", programma).list();
        for (Articolazione articolazione : articolazioni) {
            totaleDotazione = totaleDotazione.add(articolazione.getDotazioneFinanziaria());

        }
        programmaDTO.setDotazioneFinanziariaTotale(totaleDotazione);
            return Response.ok(programmaDTO).build();
    }


}
