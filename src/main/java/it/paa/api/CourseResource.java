package it.paa.api;

import it.paa.ExceptionProjectConstant;
import it.paa.dto.CourseDTO;
import it.paa.dto.DettaglioCorsoDTO;

import it.paa.model.Modulo;
import it.paa.model.TipoMetodologia;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import it.paa.model.Course;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
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
@Path("projects/course")
@ApplicationScoped
@Tag(name = "course")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CourseResource {


    /**
     * Gestisce una richiesta GET per ottenere
     * la lista dei corsi attivi presenti nel sistema.
     *  API Lista Corsi
     */
    @GET
    @Operation(summary = "Recupera la lista dei corsi ", description = "Recupera tutti i corsi .")
    @APIResponse(responseCode = "200", description = "Lista dei corsi recuperata con successo", content = @Content(mediaType = MediaType.APPLICATION_JSON))
    @APIResponse(responseCode = "404", description = "Nessun corso trovato")
    @Path("/list-course")
    @Transactional
    public List<CourseDTO> getCourses() {
        return Course.listAll().stream()
                .map(course -> Course.toDTO((Course) course))
                .collect(Collectors.toList());
    }

    /**
     * API Dettaglio corsi
     */
    @GET
    @Operation(summary = "Recupera i dettagli del corso ", description = "Recupera tutti i dettagli di un corso .")
    @APIResponse(responseCode = "200", description = "Dettagli del corso recuperati con successo", content = @Content(mediaType = MediaType.APPLICATION_JSON))
    @Path("/{codiceCorso}")
    public Response getCourseDetails(@PathParam("codiceCorso") String codiceCorso) {
        Course course = Course.find("codiceCorso", codiceCorso).firstResult();
        if (course == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(ExceptionProjectConstant.COURSE_CODE_NOT_FOUND)
                    .build();
        }

        DettaglioCorsoDTO courseDetails = mapCourseToDTO(course);
        return Response.ok(courseDetails).build();
    }




    private DettaglioCorsoDTO mapCourseToDTO(Course course) {
        DettaglioCorsoDTO dto = new DettaglioCorsoDTO();

        dto.setCodiceCorso(course.getCodiceCorso());
        dto.setTitoloCorso(course.getTitoloCorso());
        dto.setMacroCategoriaModalitaFormativa(
                course.getTipoModalitaFormativa().getClasseModalitaFormativa()
                        .getMacroCategoriaModalitaFormativa().getCodice()
        );
        dto.setClasseModalitaFormativa(course.getTipoModalitaFormativa().getClasseModalitaFormativa().getCodice());
        dto.setTipoModalitaFormativa(course.getTipoModalitaFormativa().getCodice());
        dto.setTipoContenutoFormativo(course.getTipoContenutoFormativo().getCodice());
        dto.setDataInizio(course.getDataInizio());
        dto.setDataFine(course.getDataFine());
        dto.setTipoCriterioSelezione(course.getTipoCriterioSelezione().getCodice());
        dto.setTipoAttestazioneFinale(course.getTipoAttestazioneFinale().getCodice());
        dto.setQualifica(course.getQualifica() != null ? course.getQualifica().getCodice() : "N/A");
        dto.setOreAulaFad(course.getOreAulaFad());
        dto.setOreStageTirocinio(course.getOreStageTirocinio());
        dto.setOreLaboratorio(course.getOreLaboratorio());
        dto.setDurata(course.getDurata());
        dto.setNumeroDocentiFormatoriTutor(course.getNumeroDocentiFormatoriTutor());
        dto.setMaxAllievi(course.getMaxAllievi());

        dto.setModuli(course.modulo.stream()
                .map(this::mapModuloToDTO)
                .collect(Collectors.toList()));

        return dto;
    }
    private DettaglioCorsoDTO.ModuloDTO mapModuloToDTO(Modulo modulo) {
        DettaglioCorsoDTO.ModuloDTO moduloDTO = new DettaglioCorsoDTO.ModuloDTO();

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
