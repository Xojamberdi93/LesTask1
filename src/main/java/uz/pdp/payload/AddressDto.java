package uz.pdp.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AddressDto {

    @NotNull(message = "Street must not be empty")
    private String street;

    @NotNull(message = "homeNumber must not be empty")
    private String homeNumber;

}
