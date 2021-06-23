package uz.pdp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.entity.Address;

public interface AddressRepository extends JpaRepository<Address,Integer> {
    boolean existsByStreetAndHomeNumber(String street, String homeNumber);
}
