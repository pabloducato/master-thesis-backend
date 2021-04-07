package pl.edu.prz.master.thesis.backend.controller;

import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.edu.prz.master.thesis.backend.dto.SendingInstitutionDTO;
import pl.edu.prz.master.thesis.backend.security.TokenHelper;
import pl.edu.prz.master.thesis.backend.service.SendingInstitutionService;

import javax.validation.Valid;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@Api(tags = "Sending Institution Controller")
@RequestMapping(value = "/api/sending_institutions", produces = APPLICATION_JSON_VALUE)
public class SendingInstitutionController {

    private final TokenHelper tokenHelper;
    private final SendingInstitutionService sendingInstitutionService;

    public SendingInstitutionController(TokenHelper tokenHelper, SendingInstitutionService sendingInstitutionService) {
        this.tokenHelper = tokenHelper;
        this.sendingInstitutionService = sendingInstitutionService;
    }

    @GetMapping
    public List<SendingInstitutionDTO> getSendingInstitution(@RequestParam(name = "sendingInstitutionName", required = false) String sendingInstitutionName) {
        return sendingInstitutionService.getSendingInstitutions(sendingInstitutionName);
    }

    @GetMapping("/{id}")
    public SendingInstitutionDTO getSendingInstitutionById(@PathVariable Long id) {
        return sendingInstitutionService.getSendingInstitutionById(id);
    }

    @PreAuthorize("hasAnyRole('ADMINISTRATOR')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createSendingInstitution(@Valid @RequestBody SendingInstitutionDTO sendingInstitutionDTO) {
        sendingInstitutionService.createSendingInstitution(sendingInstitutionDTO.parseSendingInstitution());
    }

    @PreAuthorize("hasAnyRole('ADMINISTRATOR')")
    @PutMapping("/{id}")
    public void updateOrAddSendingInstitution(@Valid @RequestBody SendingInstitutionDTO sendingInstitutionDTO, @PathVariable("id") Long id) {
        sendingInstitutionService.updateOrAddSendingInstitution(sendingInstitutionDTO.parseSendingInstitution(), id);
    }

    @PreAuthorize("hasAnyRole('ADMINISTRATOR')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSendingInstitution(@PathVariable("id") Long id) {
        sendingInstitutionService.deactivateSendingInstitution(id);
    }
}
