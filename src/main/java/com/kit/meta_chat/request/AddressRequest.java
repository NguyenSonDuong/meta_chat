package com.kit.meta_chat.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressRequest {

    @Size(min = 2, max = 20, message = "Nation is wrong format")
    @NotBlank
    private String nation;

    @Size(min = 2, max = 20, message = "City is wrong format")
    @NotBlank
    private String city;

    @Size(min = 2, max = 20, message = "State is wrong format")
    @NotBlank
    private String state;

    @Size(min = 2, max = 20, message = "Address 1 is wrong format")
    private String addressOne;

    @Size(min = 2, max = 20, message = "address 2 is wrong format")
    private String addressTwo;
}
