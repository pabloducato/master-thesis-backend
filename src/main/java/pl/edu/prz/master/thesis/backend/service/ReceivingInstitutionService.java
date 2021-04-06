package pl.edu.prz.master.thesis.backend.service;

import org.springframework.stereotype.Service;
import pl.edu.prz.master.thesis.backend.dto.ReceivingInstitutionDTO;
import pl.edu.prz.master.thesis.backend.entity.ReceivingInstitution;
import pl.edu.prz.master.thesis.backend.repository.ReceivingInstitutionRepository;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReceivingInstitutionService {
    private final ReceivingInstitutionRepository receivingInstitutionRepository;

    public ReceivingInstitutionService(ReceivingInstitutionRepository receivingInstitutionRepository) {
        this.receivingInstitutionRepository = receivingInstitutionRepository;
    }

    public List<ReceivingInstitutionDTO> getReceivingInstitutions(String receivingInstitutionName) {
        if (receivingInstitutionName != null && !receivingInstitutionName.equals("")) {
            return Collections.singletonList(getReceivingInstitutionByReceivingInstitutionName(receivingInstitutionName));
        }
        return receivingInstitutionRepository.findAll()
                .stream()
                .map(ReceivingInstitution::mapToDTO)
                .collect(Collectors.toList());
    }

    public ReceivingInstitutionDTO getReceivingInstitutionById(Long id) {
        return receivingInstitutionRepository.findById(id).get().mapToDTO();
    }

    public ReceivingInstitutionDTO getReceivingInstitutionByReceivingInstitutionName(String receivingInstitutionName) {
        return receivingInstitutionRepository.findByReceivingInstitutionName(receivingInstitutionName).orElseThrow(() -> new EntityNotFoundException("Unable to find receiving institution with name " + receivingInstitutionName)).mapToDTO();
    }

    public void createReceivingInstitution(ReceivingInstitution receivingInstitution) {
        receivingInstitutionRepository.save(fillEntity(receivingInstitution));
    }

    private ReceivingInstitution fillEntity(ReceivingInstitution receivingInstitution) {
        return receivingInstitution;
    }

    public void updateOrAddReceivingInstitution(ReceivingInstitution receivingInstitution, Long id) {
        receivingInstitution.setId(id);
        receivingInstitutionRepository.save(fillEntity(receivingInstitution));
    }

    public void deactivateReceivingInstitution(Long id) {
        ReceivingInstitution receivingInstitution = receivingInstitutionRepository.getOne(id);
        receivingInstitutionRepository.deleteById(receivingInstitution.getId());
    }
}
