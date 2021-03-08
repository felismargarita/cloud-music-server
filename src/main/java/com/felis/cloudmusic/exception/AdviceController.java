package com.felis.cloudmusic.exception;

import com.alibaba.fastjson.JSONObject;
import com.felis.cloudmusic.enmu.ErrorEnum;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AdviceController {

    @ExceptionHandler(CloudMusicException.class)
    public JSONObject MusicExceptionHandler(CloudMusicException ex){
        JSONObject info = new JSONObject();
        info.put("code",ErrorEnum.E_504.getErrorCode());
        info.put("msg",ex.getMessage());
        return info;
    }

    @ExceptionHandler(RuntimeException.class)
    public JSONObject RuntimeExceptionHandler(){
        JSONObject info = new JSONObject();
        info.put("code",ErrorEnum.E_503.getErrorCode());
        info.put("msg",ErrorEnum.E_503.getErrorMsg());
        return info;
    }
}
