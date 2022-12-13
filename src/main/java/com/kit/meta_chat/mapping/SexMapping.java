package com.kit.meta_chat.mapping;


import com.kit.meta_chat.model.Sex;

public class SexMapping {
    public  static Sex sexMapping(int i){
        if(i==0)
            return Sex.WOMAN;
        else if(i==1)
            return Sex.MAN;
        else if(i==2)
            return Sex.OTHER;
        else
            return null;
    }
    public  static int revertSexMapping(Sex sex){
        if(sex==Sex.WOMAN)
            return 0;
        else if(sex==Sex.MAN)
            return 1;
        else
            return 2;
    }

}
