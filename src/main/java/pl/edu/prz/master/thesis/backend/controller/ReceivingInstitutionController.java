package pl.edu.prz.master.thesis.backend.controller;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.edu.prz.master.thesis.backend.dto.ReceivingInstitutionDTO;
import pl.edu.prz.master.thesis.backend.entity.ReceivingInstitution;
import pl.edu.prz.master.thesis.backend.service.ReceivingInstitutionService;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/receiving_institutions", produces = APPLICATION_JSON_VALUE)
@Api(tags = "Receiving Institution Controller")
@CrossOrigin("*")
@AllArgsConstructor
public class ReceivingInstitutionController {

    private final ReceivingInstitutionService receivingInstitutionService;
    private final ModelMapper modelMapper;

    @GetMapping
    public List<ReceivingInstitutionDTO> getReceivingInstitution(@RequestParam(name = "receivingInstitutionName", required = false) String receivingInstitutionName) {
        return receivingInstitutionService.getReceivingInstitutions(receivingInstitutionName);
    }

    @GetMapping("/{id}")
    public ReceivingInstitutionDTO getReceivingInstitutionById(@PathVariable Long id) {
        return receivingInstitutionService.getReceivingInstitutionById(id);
    }

    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createReceivingInstitution(@Valid @RequestBody ReceivingInstitutionDTO receivingInstitutionDTO) {
        receivingInstitutionService.createReceivingInstitution(modelMapper.map(receivingInstitutionDTO, ReceivingInstitution.class));
    }

    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN')")
    @PutMapping("/{id}")
    public void updateOrAddReceivingInstitution(@Valid @RequestBody ReceivingInstitutionDTO receivingInstitutionDTO, @PathVariable("id") Long id) {
        receivingInstitutionService.updateOrAddReceivingInstitution(modelMapper.map(receivingInstitutionDTO, ReceivingInstitution.class), id);
    }

    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteReceivingInstitution(@PathVariable("id") Long id) {
        receivingInstitutionService.deactivateReceivingInstitution(id);
    }

}
