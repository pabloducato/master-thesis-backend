package pl.edu.prz.master.thesis.backend.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.edu.prz.master.thesis.backend.dto.ReceivingInstitutionDTO;
import pl.edu.prz.master.thesis.backend.entity.ReceivingInstitution;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class ReceivingInstitutionServiceImplementation implements ReceivingInstitutionService {

    @Override
    public List<ReceivingInstitutionDTO> getReceivingInstitutions(String receivingInstitutionName) {
        return null;
    }

    @Override
    public ReceivingInstitutionDTO getReceivingInstitutionById(Long id) {
        return null;
    }

    @Override
    public ReceivingInstitutionDTO getReceivingInstitutionByReceivingInstitutionName(String receivingInstitutionName) {
        return null;
    }

    @Override
    public void createReceivingInstitution(ReceivingInstitution receivingInstitution) {

    }

    @Override
    public ReceivingInstitution fillEntity(ReceivingInstitution receivingInstitution) {
        return null;
    }

    @Override
    public void updateOrAddReceivingInstitution(ReceivingInstitution receivingInstitution, Long id) {

    }

    @Override
    public void deactivateReceivingInstitution(Long id) {

    }
}
