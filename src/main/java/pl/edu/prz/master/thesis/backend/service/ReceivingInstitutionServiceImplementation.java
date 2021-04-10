package pl.edu.prz.master.thesis.backend.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import pl.edu.prz.master.thesis.backend.dto.ReceivingInstitutionDTO;
import pl.edu.prz.master.thesis.backend.entity.ReceivingInstitution;
import pl.edu.prz.master.thesis.backend.repository.ReceivingInstitutionRepository;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class ReceivingInstitutionServiceImplementation implements ReceivingInstitutionService {

    private final ReceivingInstitutionRepository receivingInstitutionRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<ReceivingInstitutionDTO> getReceivingInstitutions(String receivingInstitutionName) {
        if (receivingInstitutionName != null && !receivingInstitutionName.equals("")) {
            return Collections.singletonList(getReceivingInstitutionByReceivingInstitutionName(receivingInstitutionName));
        }
        return receivingInstitutionRepository.findAll()
                .stream()
                .map(receivingInstitution -> modelMapper.map(receivingInstitution, ReceivingInstitutionDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ReceivingInstitutionDTO getReceivingInstitutionById(Long id) {
        ReceivingInstitution receivingInstitution = receivingInstitutionRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Unable to find receiving institution with id " + id));
        return modelMapper.map(receivingInstitution, ReceivingInstitutionDTO.class);
    }

    @Override
    public ReceivingInstitutionDTO getReceivingInstitutionByReceivingInstitutionName(String receivingInstitutionName) {
        ReceivingInstitution receivingInstitution = receivingInstitutionRepository.findByName(receivingInstitutionName).orElseThrow(() -> new EntityNotFoundException("Unable to find receiving institution with name " + receivingInstitutionName));
        return modelMapper.map(receivingInstitution, ReceivingInstitutionDTO.class);
    }

    @Override
    public void createReceivingInstitution(ReceivingInstitution receivingInstitution) {
        receivingInstitutionRepository.save(fillEntity(receivingInstitution));
    }

    @Override
    public ReceivingInstitution fillEntity(ReceivingInstitution receivingInstitution) {
        return receivingInstitution;
    }

    @Override
    public void updateOrAddReceivingInstitution(ReceivingInstitution receivingInstitution, Long id) {
        receivingInstitution.setId(id);
        receivingInstitutionRepository.save(fillEntity(receivingInstitution));
    }

    @Override
    public void deactivateReceivingInstitution(Long id) {
        ReceivingInstitution receivingInstitution = receivingInstitutionRepository.getOne(id);
        receivingInstitutionRepository.deleteById(receivingInstitution.getId());
    }
}
