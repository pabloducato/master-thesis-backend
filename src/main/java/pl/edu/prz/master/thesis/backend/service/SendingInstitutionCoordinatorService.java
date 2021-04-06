package pl.edu.prz.master.thesis.backend.service;

import org.springframework.stereotype.Service;
import pl.edu.prz.master.thesis.backend.dto.SendingInstitutionCoordinatorDTO;
import pl.edu.prz.master.thesis.backend.entity.SendingInstitutionCoordinator;
import pl.edu.prz.master.thesis.backend.repository.SendingInstitutionCoordinatorRepository;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SendingInstitutionCoordinatorService {
    private final SendingInstitutionCoordinatorRepository sendingInstitutionCoordinatorRepository;

    public SendingInstitutionCoordinatorService(SendingInstitutionCoordinatorRepository sendingInstitutionCoordinatorRepository) {
        this.sendingInstitutionCoordinatorRepository = sendingInstitutionCoordinatorRepository;
    }

    public List<SendingInstitutionCoordinatorDTO> getSendingInstitutionCoordinators(String sendingInstitutionCoordinatorEmail) {
        if (sendingInstitutionCoordinatorEmail != null && !sendingInstitutionCoordinatorEmail.equals("")) {
            return Collections.singletonList(getSendingInstitutionCoordinatorByEmail(sendingInstitutionCoordinatorEmail));
        }

        return sendingInstitutionCoordinatorRepository.findAll()
                .stream()
                .map(SendingInstitutionCoordinator::mapToDTO)
                .collect(Collectors.toList());
    }

    public SendingInstitutionCoordinatorDTO getSendingInstitutionCoordinatorById(Long id) {
        return sendingInstitutionCoordinatorRepository.findById(id).get().mapToDTO();
    }


    public SendingInstitutionCoordinatorDTO getSendingInstitutionCoordinatorByEmail(String sendingInstitutionCoordinatorEmail) {
        return sendingInstitutionCoordinatorRepository.findBySendingInstitutionCoordinatorEmail(sendingInstitutionCoordinatorEmail).orElseThrow(() -> new EntityNotFoundException("Unable to find sending institution coordinator with email " + sendingInstitutionCoordinatorEmail)).mapToDTO();
    }

    public void createSendingInstitutionCoordinator(SendingInstitutionCoordinator sendingInstitutionCoordinator) {
        sendingInstitutionCoordinatorRepository.save(fillEntity(sendingInstitutionCoordinator));
    }

    private SendingInstitutionCoordinator fillEntity(SendingInstitutionCoordinator sendingInstitutionCoordinator) {
        return sendingInstitutionCoordinator;
    }

    public void updateOrAddSendingInstitutionCoordinator(SendingInstitutionCoordinator sendingInstitutionCoordinator, Long id) {
        sendingInstitutionCoordinator.setId(id);
        sendingInstitutionCoordinatorRepository.save(fillEntity(sendingInstitutionCoordinator));
    }

    public void deactivateSendingInstitutionCoordinator(Long id) {
        SendingInstitutionCoordinator sendingInstitutionCoordinator = sendingInstitutionCoordinatorRepository.getOne(id);
        sendingInstitutionCoordinatorRepository.deleteById(sendingInstitutionCoordinator.getId());
    }
}
