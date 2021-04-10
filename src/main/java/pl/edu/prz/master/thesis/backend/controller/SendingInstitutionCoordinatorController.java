package pl.edu.prz.master.thesis.backend.controller;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.edu.prz.master.thesis.backend.dto.SendingInstitutionCoordinatorDTO;
import pl.edu.prz.master.thesis.backend.entity.SendingInstitutionCoordinator;
import pl.edu.prz.master.thesis.backend.service.SendingInstitutionCoordinatorService;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@Api(tags = "Sending Institution Coordinator Controller")
@RequestMapping(value = "/api/sending_institution_coordinators", produces = APPLICATION_JSON_VALUE)
@CrossOrigin("*")
@AllArgsConstructor
public class SendingInstitutionCoordinatorController {

    private final SendingInstitutionCoordinatorService sendingInstitutionCoordinatorService;
    private final ModelMapper modelMapper;

    @GetMapping
    public List<SendingInstitutionCoordinatorDTO> getSendingInstitutionCoordinator(@RequestParam(name = "sendingInstitutionCoordinatorEmail", required = false) String sendingInstitutionCoordinatorEmail) {
        return sendingInstitutionCoordinatorService.getSendingInstitutionCoordinators(sendingInstitutionCoordinatorEmail);
    }

    @GetMapping("/{id}")
    public SendingInstitutionCoordinatorDTO getSendingInstitutionCoordinatorById(@PathVariable Long id) {
        return sendingInstitutionCoordinatorService.getSendingInstitutionCoordinatorById(id);
    }

    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createSendingInstitutionCoordinator(@Valid @RequestBody SendingInstitutionCoordinatorDTO sendingInstitutionCoordinatorDTO) {
        sendingInstitutionCoordinatorService.createSendingInstitutionCoordinator(modelMapper.map(sendingInstitutionCoordinatorDTO, SendingInstitutionCoordinator.class));
    }

    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN')")
    @PutMapping("/{id}")
    public void updateOrAddSendingInstitutionCoordinator(@Valid @RequestBody SendingInstitutionCoordinatorDTO sendingInstitutionCoordinatorDTO, @PathVariable("id") Long id) {
        sendingInstitutionCoordinatorService.updateOrAddSendingInstitutionCoordinator(modelMapper.map(sendingInstitutionCoordinatorDTO, SendingInstitutionCoordinator.class), id);
    }

    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSendingInstitutionCoordinator(@PathVariable("id") Long id) {
        sendingInstitutionCoordinatorService.deactivateSendingInstitutionCoordinator(id);
    }

}
