package com.kit.meta_chat.request;

import com.kit.meta_chat.model.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserInforRequest {

    @Size(min = 2, max = 20, message = "Full name is wrong format")
    private String fullname;

    @Size(min = 10, max = 15, message = "Phone number is wrong format")
    private String phoneNumber;

    @Valid
    private AddressRequest address;

    private Date birthday;

    @Size(min = 10, max = 15, message = "Hobby is wrong format")
    private String hobbies;

    @Size(min = 10, max = 15, message = "Work is wrong format")
    private String work;

    @Size(min = 10, max = 15, message = "School is wrong format")
    private String school;
}
