package dev.harshit.traini8.models;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class TrainingCenter extends BaseModel {

    /**
     * Class represents a Training Center entity, including details such as
     * center name, center code, address, student capacity, courses offered,
     * contact email, and phone number, with appropriate validation constraints.
     */


    @NotNull(message = "CenterName is required")
    @Size(max = 40, message = "CenterName must be less than 40 characters")
    private String centerName;

    @NotNull(message = "CenterCode is required")
    @Size(min = 12, max = 12, message = "CenterCode must be exactly 12 characters")
    private String centerCode;

    @Valid
    @NotNull(message = "Address is required")
    @Embedded
    private Address address;

    @Min(value = 1, message = "Student capacity must be at least 1")
    private Integer studentCapacity;

    @ElementCollection
    private List<String> coursesOffered;

    @NotNull(message = "Invalid email format")
    private String contactEmail;

    @NotNull(message = "Contact phone is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits")
    private String contactPhone;
}
