package com.example.springtutorial.springbootweb.dtos;

import com.example.springtutorial.springbootweb.annotations.EmployeeRoleValidation;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeDto {

    private Long id;

    @NotBlank(message = "Name  is required field ")
    @Size(min=3,max = 10 ,message = "Number of Characters in name should be in the range : {3,10}")
    private String name;

    @NotBlank(message = "email should not be blank")
    @Email(message = "Email should be a valid email")
    private String email;

    @NotNull(message = "age should be not null")
    @Max(value = 80 ,message = "Age can't be greater than 80")
    @Min(value = 18 ,message = "Age can't be less than 18")
    private Integer age;

    @PastOrPresent(message = "date of joining should not be in the future")
    private LocalDate dateOfJoining;

    @NotBlank(message = "the role of employee can't be blank")
    //@Pattern(regexp = "^(ADMIN|USER)$",message = "the role of employee can be USER or ADMIN")
    @EmployeeRoleValidation
    private String role;

    @NotNull(message = "salary should not be null")
    @Positive(message = "salary of employee should not be negative")
    @Digits(integer = 6,fraction = 2,message = "salary can be in the form of xxxxxx.yy")
    @DecimalMax(value = "100000.99")
    @DecimalMin(value = "100.50")
    private Double salary;

    @AssertTrue(message = "Employee should be active")
    @JsonProperty("isActive")
    private Boolean isActive;

}
