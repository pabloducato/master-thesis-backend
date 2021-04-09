package pl.edu.prz.master.thesis.backend.service;

import pl.edu.prz.master.thesis.backend.dto.ReceivingInstitutionDTO;
import pl.edu.prz.master.thesis.backend.entity.ReceivingInstitution;

import java.util.List;

public interface ReceivingInstitutionService {

    List<ReceivingInstitutionDTO> getReceivingInstitutions(String receivingInstitutionName);

    ReceivingInstitutionDTO getReceivingInstitutionById(Long id);

    ReceivingInstitutionDTO getReceivingInstitutionByReceivingInstitutionName(String receivingInstitutionName);

    void createReceivingInstitution(ReceivingInstitution receivingInstitution);

    ReceivingInstitution fillEntity(ReceivingInstitution receivingInstitution);

    void updateOrAddReceivingInstitution(ReceivingInstitution receivingInstitution, Long id);

    void deactivateReceivingInstitution(Long id);

}
