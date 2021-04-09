package pl.edu.prz.master.thesis.backend.service;

import pl.edu.prz.master.thesis.backend.dto.SendingInstitutionCoordinatorDTO;
import pl.edu.prz.master.thesis.backend.entity.SendingInstitutionCoordinator;

import java.util.List;

public interface SendingInstitutionCoordinatorService {

    List<SendingInstitutionCoordinatorDTO> getSendingInstitutionCoordinators(String sendingInstitutionCoordinatorEmail);

    SendingInstitutionCoordinatorDTO getSendingInstitutionCoordinatorById(Long id);

    SendingInstitutionCoordinatorDTO getSendingInstitutionCoordinatorByEmail(String sendingInstitutionCoordinatorEmail);

    void createSendingInstitutionCoordinator(SendingInstitutionCoordinator sendingInstitutionCoordinator);

    SendingInstitutionCoordinator fillEntity(SendingInstitutionCoordinator sendingInstitutionCoordinator);

    void updateOrAddSendingInstitutionCoordinator(SendingInstitutionCoordinator sendingInstitutionCoordinator, Long id);

    void deactivateSendingInstitutionCoordinator(Long id);

}
