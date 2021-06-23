package uz.pdp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.entity.Address;
import uz.pdp.payload.AddressDto;
import uz.pdp.service.AddressService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/address")
public class AddressController {

    @Autowired
    AddressService addressService;

    @GetMapping

    public ResponseEntity<List<Address>> getAllAddress() {
       return   addressService.getAddress();

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAddress(@PathVariable Integer id) {
      return   addressService.getAddressById(id);
    }

    @PostMapping
    public ResponseEntity<?> addAddress(@Valid @RequestBody AddressDto addressDto) {
        return addressService.addAddress(addressDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editAddress(@Valid @RequestBody AddressDto addressDto, @PathVariable Integer id) {
      return   addressService.edit(addressDto, id);
    }

    @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteAddress(@PathVariable Integer id){
        return addressService.deleteAddress(id);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
