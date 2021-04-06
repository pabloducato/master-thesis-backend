package pl.edu.prz.master.thesis.backend.service;

import org.springframework.stereotype.Service;
import pl.edu.prz.master.thesis.backend.dto.SendingInstitutionDTO;
import pl.edu.prz.master.thesis.backend.entity.SendingInstitution;
import pl.edu.prz.master.thesis.backend.repository.SendingInstitutionRepository;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SendingInstitutionService {
    private final SendingInstitutionRepository sendingInstitutionRepository;

    public SendingInstitutionService(SendingInstitutionRepository sendingInstitutionRepository) {
        this.sendingInstitutionRepository = sendingInstitutionRepository;
    }

    public List<SendingInstitutionDTO> getSendingInstitutions(String sendingInstitutionName) {
        if (sendingInstitutionName != null && !sendingInstitutionName.equals("")) {
            return Collections.singletonList(getSendingInstitutionBySendingInstitutionName(sendingInstitutionName));
        }
        return sendingInstitutionRepository.findAll()
                .stream()
                .map(SendingInstitution::mapToDTO)
                .collect(Collectors.toList());
    }

    public SendingInstitutionDTO getSendingInstitutionById(Long id) {
        return sendingInstitutionRepository.findById(id).get().mapToDTO();
    }

    public SendingInstitutionDTO getSendingInstitutionBySendingInstitutionName(String sendingInstitutionName) {
        return sendingInstitutionRepository.findBySendingInstitutionName(sendingInstitutionName).orElseThrow(() -> new EntityNotFoundException("Unable to find sending institution with name " + sendingInstitutionName)).mapToDTO();
    }

    public void createSendingInstitution(SendingInstitution sendingInstitution) {
        sendingInstitutionRepository.save(fillEntity(sendingInstitution));
    }

    private SendingInstitution fillEntity(SendingInstitution sendingInstitution) {
        return sendingInstitution;
    }

    public void updateOrAddSendingInstitution(SendingInstitution sendingInstitution, Long id) {
        sendingInstitution.setId(id);
        sendingInstitutionRepository.save(fillEntity(sendingInstitution));
    }

    public void deactivateSendingInstitution(Long id) {
        SendingInstitution sendingInstitution = sendingInstitutionRepository.getOne(id);
        sendingInstitutionRepository.deleteById(sendingInstitution.getId());
    }
}
