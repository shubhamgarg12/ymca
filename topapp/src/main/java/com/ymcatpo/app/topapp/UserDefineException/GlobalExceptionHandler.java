package com.ymcatpo.app.topapp.UserDefineException;

import java.util.Date;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;



@RestController
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	 
	@ExceptionHandler(ApiException.class)
	  public final ErrorDetails handleException(ApiException ex)  {
	    ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(),ex.getStatus().value());
	    return errorDetails;
	  }
	
	@ExceptionHandler(Exception.class)
	public final ErrorDetails handleAllExceptions(Exception ex) {
	  ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(),500);
	  return errorDetails;
	}	
	
}
