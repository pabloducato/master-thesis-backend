package pl.edu.prz.master.thesis.backend.service;

import pl.edu.prz.master.thesis.backend.dto.SendingInstitutionDTO;
import pl.edu.prz.master.thesis.backend.entity.SendingInstitution;

import java.util.List;

public interface SendingInstitutionService {

    List<SendingInstitutionDTO> getSendingInstitutions(String sendingInstitutionName);

    SendingInstitutionDTO getSendingInstitutionById(Long id);

    SendingInstitutionDTO getSendingInstitutionBySendingInstitutionName(String sendingInstitutionName);

    void createSendingInstitution(SendingInstitution sendingInstitution);

    SendingInstitution fillEntity(SendingInstitution sendingInstitution);

    void updateOrAddSendingInstitution(SendingInstitution sendingInstitution, Long id);

    void deactivateSendingInstitution(Long id);

}
