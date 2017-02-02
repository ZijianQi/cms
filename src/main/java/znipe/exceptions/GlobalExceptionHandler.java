package znipe.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import java.io.FileNotFoundException;
import java.nio.file.FileAlreadyExistsException;

/**
 * Created by Everlasting on 2017-01-29.
 */

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Data is not found")
    @ExceptionHandler(FileNotFoundException.class)
    public ModelAndView FileNotFoundExceptionHandler(Exception exception) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", exception);
        mav.setViewName("error");
        return mav;
    }

    @ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE, reason = "Argument is invalid")
    @ExceptionHandler(IllegalArgumentException.class)
    public ModelAndView IllegalArgumentExceptionHandler(Exception exception) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", exception);
        mav.setViewName("error");
        return mav;
    }

    @ResponseStatus(value = HttpStatus.CONFLICT, reason = "The data exists already")
    @ExceptionHandler(FileAlreadyExistsException.class)
    public ModelAndView FileAlreadyExistsExceptionHandler(Exception exception) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", exception);
        mav.setViewName("error");
        return mav;
    }
}


