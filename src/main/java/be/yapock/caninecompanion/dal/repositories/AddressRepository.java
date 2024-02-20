package be.yapock.caninecompanion.dal.repositories;

import be.yapock.caninecompanion.dal.models.Address;
import be.yapock.caninecompanion.dal.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
