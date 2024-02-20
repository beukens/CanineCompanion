package be.yapock.caninecompanion.dal.repositories;

import be.yapock.caninecompanion.dal.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
