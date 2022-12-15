package com.kit.meta_chat.mapping;

import com.kit.meta_chat.model.UserInfo;
import com.kit.meta_chat.request.UpdateUserInforRequest;

public class UserInfoMapping {
    public static UserInfo requestMapping(UpdateUserInforRequest userInforRequest){
        try {
            UserInfo userInfo = new UserInfo();
            userInfo.setAddress(AddressMapping.requestMapping(userInforRequest.getAddress()));
            userInfo.setFullname(userInforRequest.getFullname());
            userInfo.setBirthday(userInforRequest.getBirthday());
            userInfo.setSchool(userInforRequest.getSchool());
            userInfo.setHobbies(userInforRequest.getHobbies());
            userInfo.setWork(userInforRequest.getWork());
            userInfo.setPhoneNumber(userInforRequest.getPhoneNumber());
            return  userInfo;
        }catch (Exception ex){
            return null;
        }
    }
}
