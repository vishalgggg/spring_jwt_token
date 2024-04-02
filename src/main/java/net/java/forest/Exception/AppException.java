package net.java.forest.Exception;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Controller
@ControllerAdvice
public class AppException {

    @ExceptionHandler(value = UserNotFound.class)
    public String handleUserNotFoundException()
    {
        return "user not found";
    }

}
