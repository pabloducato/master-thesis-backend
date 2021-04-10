package pl.edu.prz.master.thesis.backend.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import pl.edu.prz.master.thesis.backend.dto.SendingInstitutionDTO;
import pl.edu.prz.master.thesis.backend.entity.SendingInstitution;
import pl.edu.prz.master.thesis.backend.repository.SendingInstitutionRepository;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class SendingInstitutionServiceImplementation implements SendingInstitutionService {

    private final SendingInstitutionRepository sendingInstitutionRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<SendingInstitutionDTO> getSendingInstitutions(String sendingInstitutionName) {
        if (sendingInstitutionName != null && !sendingInstitutionName.equals("")) {
            return Collections.singletonList(getSendingInstitutionBySendingInstitutionName(sendingInstitutionName));
        }
        return sendingInstitutionRepository.findAll()
                .stream()
                .map(sendingInstitution -> modelMapper.map(sendingInstitution, SendingInstitutionDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public SendingInstitutionDTO getSendingInstitutionById(Long id) {
        SendingInstitution sendingInstitution = sendingInstitutionRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Unable to find student with id " + id));
        return modelMapper.map(sendingInstitution, SendingInstitutionDTO.class);
    }

    @Override
    public SendingInstitutionDTO getSendingInstitutionBySendingInstitutionName(String name) {
        SendingInstitution sendingInstitution = sendingInstitutionRepository.findByName(name).orElseThrow(() -> new EntityNotFoundException("Unable to find sending institution with name " + name));
        return modelMapper.map(sendingInstitution, SendingInstitutionDTO.class);
    }

    @Override
    public void createSendingInstitution(SendingInstitution sendingInstitution) {
        sendingInstitutionRepository.save(fillEntity(sendingInstitution));
    }

    @Override
    public SendingInstitution fillEntity(SendingInstitution sendingInstitution) {
        return sendingInstitution;
    }

    @Override
    public void updateOrAddSendingInstitution(SendingInstitution sendingInstitution, Long id) {
        sendingInstitution.setId(id);
        sendingInstitutionRepository.save(fillEntity(sendingInstitution));
    }

    @Override
    public void deactivateSendingInstitution(Long id) {
        SendingInstitution sendingInstitution = sendingInstitutionRepository.getOne(id);
        sendingInstitutionRepository.deleteById(sendingInstitution.getId());
    }
}
