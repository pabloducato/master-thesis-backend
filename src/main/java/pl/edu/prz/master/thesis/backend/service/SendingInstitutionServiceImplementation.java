package pl.edu.prz.master.thesis.backend.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.edu.prz.master.thesis.backend.dto.SendingInstitutionDTO;
import pl.edu.prz.master.thesis.backend.entity.SendingInstitution;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class SendingInstitutionServiceImplementation implements SendingInstitutionService {

    @Override
    public List<SendingInstitutionDTO> getSendingInstitutions(String sendingInstitutionName) {
        return null;
    }

    @Override
    public SendingInstitutionDTO getSendingInstitutionById(Long id) {
        return null;
    }

    @Override
    public SendingInstitutionDTO getSendingInstitutionBySendingInstitutionName(String sendingInstitutionName) {
        return null;
    }

    @Override
    public void createSendingInstitution(SendingInstitution sendingInstitution) {

    }

    @Override
    public SendingInstitution fillEntity(SendingInstitution sendingInstitution) {
        return null;
    }

    @Override
    public void updateOrAddSendingInstitution(SendingInstitution sendingInstitution, Long id) {

    }

    @Override
    public void deactivateSendingInstitution(Long id) {

    }
}
