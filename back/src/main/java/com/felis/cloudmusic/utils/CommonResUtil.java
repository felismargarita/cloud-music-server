package com.felis.cloudmusic.utils;

import com.felis.cloudmusic.entity.ResResult;

public class CommonResUtil {
    public static ResResult success(){
        return  new ResResult(200,"success",null);
    }
    public static ResResult success(Object info){
        return  new ResResult(200,"success",info);
    }

}
