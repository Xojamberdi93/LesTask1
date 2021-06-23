package uz.pdp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.entity.Address;
import uz.pdp.payload.AddressDto;
import uz.pdp.repository.AddressRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    AddressRepository addressRepository;


    public ResponseEntity<List<Address>> getAddress() {
        List<Address> addressList = addressRepository.findAll();
        return ResponseEntity.ok(addressList);
    }


    public ResponseEntity<?> getAddressById(Integer id) {
        Optional<Address> address = addressRepository.findById(id);
        if (!address.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Address not found");
        return ResponseEntity.status(200).body(address);
    }

    public ResponseEntity<?> addAddress(AddressDto addressDto) {
        boolean exists = addressRepository.existsByStreetAndHomeNumber(addressDto.getStreet(), addressDto.getHomeNumber());
        if (exists) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Address already exist");
        }
        Address address = new Address();
        address.setStreet(addressDto.getStreet());
        address.setHomeNumber(addressDto.getHomeNumber());
        addressRepository.save(address);
        return ResponseEntity.status(200).body("Address added");
    }

    public ResponseEntity<?> edit(AddressDto addressDto, Integer id) {
        Optional<Address> address = addressRepository.findById(id);
        if (!address.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Address not found");
        }
        Address address1 = address.get();
        address1.setStreet(addressDto.getStreet());
        address1.setHomeNumber(addressDto.getHomeNumber());
        addressRepository.save(address1);
        return ResponseEntity.status(202).body("Address edited");
    }

    public ResponseEntity<?> deleteAddress(Integer id) {
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (!optionalAddress.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Address not found");
        addressRepository.deleteById(id);
        return ResponseEntity.status(202).body("Address deleted");
    }
}
