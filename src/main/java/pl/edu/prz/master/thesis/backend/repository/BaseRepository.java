package pl.edu.prz.master.thesis.backend.repository;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import java.io.Serializable;
import java.util.Optional;

public class BaseRepository<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> {

    public BaseRepository(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
    }

    @Override
    public Optional<T> findById(ID id) {
        return Optional.of(super.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Unable to find " + this.getDomainClass().getName() + " with id " + id)));
    }
}
