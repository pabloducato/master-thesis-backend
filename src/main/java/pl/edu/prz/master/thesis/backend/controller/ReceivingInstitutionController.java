package pl.edu.prz.master.thesis.backend.controller;

import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.edu.prz.master.thesis.backend.dto.ReceivingInstitutionDTO;
import pl.edu.prz.master.thesis.backend.security.TokenHelper;
import pl.edu.prz.master.thesis.backend.service.ReceivingInstitutionService;

import javax.validation.Valid;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@Api(tags = "Receiving Institution Controller")
@RequestMapping(value = "/api/receiving_institutions", produces = APPLICATION_JSON_VALUE)
public class ReceivingInstitutionController {

    private final TokenHelper tokenHelper;
    private final ReceivingInstitutionService receivingInstitutionService;

    public ReceivingInstitutionController(TokenHelper tokenHelper, ReceivingInstitutionService receivingInstitutionService) {
        this.tokenHelper = tokenHelper;
        this.receivingInstitutionService = receivingInstitutionService;
    }

    @GetMapping
    public List<ReceivingInstitutionDTO> getReceivingInstitution(@RequestParam(name = "receivingInstitutionName", required = false) String receivingInstitutionName) {
        return receivingInstitutionService.getReceivingInstitutions(receivingInstitutionName);
    }

    @GetMapping("/{id}")
    public ReceivingInstitutionDTO getReceivingInstitutionById(@PathVariable Long id) {
        return receivingInstitutionService.getReceivingInstitutionById(id);
    }

    @PreAuthorize("hasAnyRole('ADMINISTRATOR')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createReceivingInstitution(@Valid @RequestBody ReceivingInstitutionDTO receivingInstitutionDTO) {
        receivingInstitutionService.createReceivingInstitution(receivingInstitutionDTO.parseReceivingInstitution());
    }

    @PreAuthorize("hasAnyRole('ADMINISTRATOR')")
    @PutMapping("/{id}")
    public void updateOrAddReceivingInstitution(@Valid @RequestBody ReceivingInstitutionDTO receivingInstitutionDTO, @PathVariable("id") Long id) {
        receivingInstitutionService.updateOrAddReceivingInstitution(receivingInstitutionDTO.parseReceivingInstitution(), id);
    }

    @PreAuthorize("hasAnyRole('ADMINISTRATOR')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteReceivingInstitution(@PathVariable("id") Long id) {
        receivingInstitutionService.deactivateReceivingInstitution(id);
    }
}
