package pl.edu.prz.master.thesis.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.edu.prz.master.thesis.backend.dto.SendingInstitutionCoordinatorDTO;
import pl.edu.prz.master.thesis.backend.repository.SendingInstitutionCoordinatorRepository;
import pl.edu.prz.master.thesis.backend.security.TokenHelper;
import pl.edu.prz.master.thesis.backend.service.SendingInstitutionCoordinatorService;

import javax.validation.Valid;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/sending_institution_coordinators", produces = APPLICATION_JSON_VALUE)
public class SendingInstitutionCoordinatorController {

    private final SendingInstitutionCoordinatorService sendingInstitutionCoordinatorService;
    private final TokenHelper tokenHelper;
    private final SendingInstitutionCoordinatorRepository sendingInstitutionCoordinatorRepository;

    public SendingInstitutionCoordinatorController(SendingInstitutionCoordinatorService sendingInstitutionCoordinatorService, TokenHelper tokenHelper, SendingInstitutionCoordinatorRepository sendingInstitutionCoordinatorRepository) {
        this.sendingInstitutionCoordinatorService = sendingInstitutionCoordinatorService;
        this.tokenHelper = tokenHelper;
        this.sendingInstitutionCoordinatorRepository = sendingInstitutionCoordinatorRepository;
    }

    @GetMapping
    public List<SendingInstitutionCoordinatorDTO> getSendingInstitutionCoordinator(@RequestParam(name = "sendingInstitutionCoordinatorEmail", required = false) String sendingInstitutionCoordinatorEmail) {
        return sendingInstitutionCoordinatorService.getSendingInstitutionCoordinators(sendingInstitutionCoordinatorEmail);
    }

    @GetMapping("/{id}")
    public SendingInstitutionCoordinatorDTO getSendingInstitutionCoordinatorById(@PathVariable Long id) {
        return sendingInstitutionCoordinatorService.getSendingInstitutionCoordinatorById(id);
    }

    @PreAuthorize("hasAnyRole('ADMINISTRATOR')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createSendingInstitutionCoordinator(@Valid @RequestBody SendingInstitutionCoordinatorDTO sendingInstitutionCoordinatorDTO) {
        sendingInstitutionCoordinatorService.createSendingInstitutionCoordinator(sendingInstitutionCoordinatorDTO.parseSendingInstitutionCoordinator());
    }

    @PreAuthorize("hasAnyRole('ADMINISTRATOR')")
    @PutMapping("/{id}")
    public void updateOrAddSendingInstitutionCoordinator(@Valid @RequestBody SendingInstitutionCoordinatorDTO sendingInstitutionCoordinatorDTO, @PathVariable("id") Long id) {
        sendingInstitutionCoordinatorService.updateOrAddSendingInstitutionCoordinator(sendingInstitutionCoordinatorDTO.parseSendingInstitutionCoordinator(), id);
    }

    @PreAuthorize("hasAnyRole('ADMINISTRATOR')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSendingInstitutionCoordinator(@PathVariable("id") Long id) {
        sendingInstitutionCoordinatorService.deactivateSendingInstitutionCoordinator(id);
    }
}
