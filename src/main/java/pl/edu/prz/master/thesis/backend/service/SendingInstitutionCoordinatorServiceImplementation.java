package pl.edu.prz.master.thesis.backend.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import pl.edu.prz.master.thesis.backend.dto.SendingInstitutionCoordinatorDTO;
import pl.edu.prz.master.thesis.backend.entity.SendingInstitutionCoordinator;
import pl.edu.prz.master.thesis.backend.repository.SendingInstitutionCoordinatorRepository;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class SendingInstitutionCoordinatorServiceImplementation implements SendingInstitutionCoordinatorService {

    private final SendingInstitutionCoordinatorRepository sendingInstitutionCoordinatorRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<SendingInstitutionCoordinatorDTO> getSendingInstitutionCoordinators(String sendingInstitutionCoordinatorEmail) {
        if (sendingInstitutionCoordinatorEmail != null && !sendingInstitutionCoordinatorEmail.equals("")) {
            return Collections.singletonList(getSendingInstitutionCoordinatorByEmail(sendingInstitutionCoordinatorEmail));
        }

        return sendingInstitutionCoordinatorRepository.findAll()
                .stream()
                .map(sendingInstitutionCoordinator -> modelMapper.map(sendingInstitutionCoordinator, SendingInstitutionCoordinatorDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public SendingInstitutionCoordinatorDTO getSendingInstitutionCoordinatorById(Long id) {
        SendingInstitutionCoordinator sendingInstitutionCoordinator = sendingInstitutionCoordinatorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Unable to find sending institution coordinator with id " + id));
        return modelMapper.map(sendingInstitutionCoordinator, SendingInstitutionCoordinatorDTO.class);
    }

    @Override
    public SendingInstitutionCoordinatorDTO getSendingInstitutionCoordinatorByEmail(String email) {
        SendingInstitutionCoordinator sendingInstitutionCoordinator = sendingInstitutionCoordinatorRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException("Unable to find sending institution coordinator with email " + email));
        return modelMapper.map(sendingInstitutionCoordinator, SendingInstitutionCoordinatorDTO.class);
    }

    @Override
    public void createSendingInstitutionCoordinator(SendingInstitutionCoordinator sendingInstitutionCoordinator) {
        sendingInstitutionCoordinatorRepository.save(fillEntity(sendingInstitutionCoordinator));
    }

    @Override
    public SendingInstitutionCoordinator fillEntity(SendingInstitutionCoordinator sendingInstitutionCoordinator) {
        return sendingInstitutionCoordinator;
    }

    @Override
    public void updateOrAddSendingInstitutionCoordinator(SendingInstitutionCoordinator sendingInstitutionCoordinator, Long id) {
        sendingInstitutionCoordinator.setId(id);
        sendingInstitutionCoordinatorRepository.save(fillEntity(sendingInstitutionCoordinator));
    }

    @Override
    public void deactivateSendingInstitutionCoordinator(Long id) {
        SendingInstitutionCoordinator sendingInstitutionCoordinator = sendingInstitutionCoordinatorRepository.getOne(id);
        sendingInstitutionCoordinatorRepository.deleteById(sendingInstitutionCoordinator.getId());
    }
}
