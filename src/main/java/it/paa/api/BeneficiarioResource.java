package it.paa.api;

import io.quarkus.security.Authenticated;
import it.paa.ExceptionProjectConstant;
import it.paa.model.SoggettoCorrelato;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import it.paa.model.Anagrafica;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import java.net.URI;
import java.util.List;
import java.util.Map;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;


@Path("/beneficiary")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class BeneficiarioResource {

    /**
     * API Verifica Beneficiario
     * Verifica se un beneficiario è associato a uno o più progetti
     */
    @GET
    @Path("/check-projects")
    @Operation(summary = "Verifica se il beneficiario è associato ad almeno un progetto")
    @APIResponse(responseCode = "200", description = "Il beneficiario è associato ad almeno un progetto",
            content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = URI.class)))
    @APIResponse(responseCode = "404", description = "Il beneficiario non è associato ad alcun progetto.")
    public Response verificaBeneficiario(@Parameter(description = "cfPiva", required = true)
                                             @QueryParam("cfPiva") String cfPiva) {
        if (cfPiva == null || cfPiva.isBlank()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(ExceptionProjectConstant.GENERIC_ERROR_CF_PIVA)
                    .build();
        }

        // Trova il beneficiario (Anagrafica) a partir dal CF/P.IVA
        Anagrafica beneficiario = Anagrafica.find("cfPiva = ?1", cfPiva).firstResult();

        if (beneficiario == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(ExceptionProjectConstant.BENEFICIARIO_NOT_FOUND)
                    .build();
        }

        // Recupera i progetti associati al beneficiario tramite SoggettoCorrelato
        List<String> titoliProgetti = SoggettoCorrelato.find("anagrafica.id = ?1", beneficiario.id)
                .stream()
                .map(sc -> ((SoggettoCorrelato) sc).progetto.titolo) // Ottieni il titolo del progetto
                .toList();

        if (!titoliProgetti.isEmpty()) {
            return Response.ok(Map.of(
                    "message", "Il beneficiario è associato ai seguenti progetti.",
                    "progettiAssociati", titoliProgetti
            )).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Map.of("message", "Il beneficiario non è associato ad alcun progetto."))
                    .build();
        }
    }



}
