package pl.edu.prz.master.thesis.backend.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.edu.prz.master.thesis.backend.dto.SendingInstitutionCoordinatorDTO;
import pl.edu.prz.master.thesis.backend.entity.SendingInstitutionCoordinator;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class SendingInstitutionCoordinatorServiceImplementation implements SendingInstitutionCoordinatorService {

    @Override
    public List<SendingInstitutionCoordinatorDTO> getSendingInstitutionCoordinators(String sendingInstitutionCoordinatorEmail) {
        return null;
    }

    @Override
    public SendingInstitutionCoordinatorDTO getSendingInstitutionCoordinatorById(Long id) {
        return null;
    }

    @Override
    public SendingInstitutionCoordinatorDTO getSendingInstitutionCoordinatorByEmail(String sendingInstitutionCoordinatorEmail) {
        return null;
    }

    @Override
    public void createSendingInstitutionCoordinator(SendingInstitutionCoordinator sendingInstitutionCoordinator) {

    }

    @Override
    public SendingInstitutionCoordinator fillEntity(SendingInstitutionCoordinator sendingInstitutionCoordinator) {
        return null;
    }

    @Override
    public void updateOrAddSendingInstitutionCoordinator(SendingInstitutionCoordinator sendingInstitutionCoordinator, Long id) {

    }

    @Override
    public void deactivateSendingInstitutionCoordinator(Long id) {

    }
}
