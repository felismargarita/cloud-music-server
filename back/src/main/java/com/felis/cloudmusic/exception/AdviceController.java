package com.felis.cloudmusic.exception;

import com.felis.cloudmusic.enmu.ErrorEnum;
import com.felis.cloudmusic.entity.ResResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AdviceController {

    @ExceptionHandler(CloudMusicException.class)
    public ResResult MusicExceptionHandler(CloudMusicException ex){
        ex.printStackTrace();
        return new ResResult(ErrorEnum.E_504.getErrorCode(),ex.getMessage(),null);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResResult RuntimeExceptionHandler(RuntimeException re){
        re.printStackTrace();
        ErrorEnum commonError = ErrorEnum.E_504;
        return new ResResult(commonError.getErrorCode(),re.getMessage(),null);
    }
}
