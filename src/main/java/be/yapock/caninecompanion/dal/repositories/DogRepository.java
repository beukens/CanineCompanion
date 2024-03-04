package be.yapock.caninecompanion.dal.repositories;

import be.yapock.caninecompanion.dal.models.Dog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DogRepository extends JpaRepository<Dog, Long>, JpaSpecificationExecutor<Dog>{
    List<Dog> findAllByOwner(long id);
}
