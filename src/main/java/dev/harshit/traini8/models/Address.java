package dev.harshit.traini8.models;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Address {

    /**
     * Class represents an embeddable address entity that includes details like
     * detailed address, city, state, and pin code, with validation constraints.
     */

    @NotNull(message = "Detailed address required")
    @Size(max = 250, message = "Detailed address must be less than 250 characters")
    private String detailedAddress;

    @NotNull(message = "City is required")
    private String city;

    @NotNull(message = "State is required")
    private String state;

    @NotNull(message = "PinCode is required")
    @Size(min = 6, max = 6, message = "PinCode must be 6 characters")
    private String pinCode;
}
