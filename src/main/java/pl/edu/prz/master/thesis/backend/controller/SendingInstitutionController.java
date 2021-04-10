package pl.edu.prz.master.thesis.backend.controller;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.edu.prz.master.thesis.backend.dto.SendingInstitutionDTO;
import pl.edu.prz.master.thesis.backend.entity.SendingInstitution;
import pl.edu.prz.master.thesis.backend.service.SendingInstitutionService;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/sending_institutions", produces = APPLICATION_JSON_VALUE)
@Api(tags = "Sending Institution Controller")
@CrossOrigin("*")
@AllArgsConstructor
public class SendingInstitutionController {

    private final SendingInstitutionService sendingInstitutionService;
    private final ModelMapper modelMapper;

    @GetMapping
    public List<SendingInstitutionDTO> getSendingInstitution(@RequestParam(name = "sendingInstitutionName", required = false) String sendingInstitutionName) {
        return sendingInstitutionService.getSendingInstitutions(sendingInstitutionName);
    }

    @GetMapping("/{id}")
    public SendingInstitutionDTO getSendingInstitutionById(@PathVariable Long id) {
        return sendingInstitutionService.getSendingInstitutionById(id);
    }

    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createSendingInstitution(@Valid @RequestBody SendingInstitutionDTO sendingInstitutionDTO) {
        sendingInstitutionService.createSendingInstitution(modelMapper.map(sendingInstitutionDTO, SendingInstitution.class));
    }

    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN')")
    @PutMapping("/{id}")
    public void updateOrAddSendingInstitution(@Valid @RequestBody SendingInstitutionDTO sendingInstitutionDTO, @PathVariable("id") Long id) {
        sendingInstitutionService.updateOrAddSendingInstitution(modelMapper.map(sendingInstitutionDTO, SendingInstitution.class), id);
    }

    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSendingInstitution(@PathVariable("id") Long id) {
        sendingInstitutionService.deactivateSendingInstitution(id);
    }

}
