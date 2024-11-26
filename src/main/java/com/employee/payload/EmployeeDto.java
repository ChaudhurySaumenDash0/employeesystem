package com.employee.payload;


import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.aspectj.bridge.IMessage;

@Getter
@Setter
public class EmployeeDto {
    @Id
    private Long id;
    //@Column(name = "name", nullable = false)
    @NotBlank
    @Size(min=3, message="At least 2 Characters Required")
    private String name;
    //@Column(name = "email", nullable = false)
    @Email
    private String email;
    //@Column(name = "phone", nullable = false)
    @Size(min=10, max=10, message="Phone number should be 10 digits")
    private String phone;
}

