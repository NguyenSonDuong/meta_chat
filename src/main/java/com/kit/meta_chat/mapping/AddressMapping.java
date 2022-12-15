package com.kit.meta_chat.mapping;

import com.kit.meta_chat.model.Address;
import com.kit.meta_chat.request.AddressRequest;

public class AddressMapping {
    public static Address requestMapping(AddressRequest addressRequest){
        try{
            Address address = new Address();
            address.setNation(addressRequest.getNation());
            address.setCity(addressRequest.getCity());
            address.setState(addressRequest.getState());
            address.setAddressOne(addressRequest.getAddressOne());
            address.setAddressTwo(addressRequest.getAddressTwo());
            return address;
        }catch (Exception ex){
            return null;
        }
    }
}
