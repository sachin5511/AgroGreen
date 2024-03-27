package com.jsp.agro.exceptition;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.jsp.agro.util.ResponseStructure;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class AgroExceptionHandle extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(EmailNotFound.class)
	public ResponseEntity<ResponseStructure<String> >emailNotFound(EmailNotFound ex){
		ResponseStructure<String> m = new ResponseStructure<String>();
		m.setData("Data not found");
		m.setMsg(ex.getMsg());
		m.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(m,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(PasswordMissmatched.class)
	public ResponseEntity<ResponseStructure<String> >passwordMismatched(PasswordMissmatched pwd){
		ResponseStructure<String> m = new ResponseStructure<String>();
		m.setData("Data Not Found....");
		m.setMsg(pwd.getMsg());
		m.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(m,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(DataNotFound.class)
	public ResponseEntity<ResponseStructure<String> >dataNotFound(DataNotFound id){
		ResponseStructure<String> m = new ResponseStructure<String>();
		m.setData("Data is Not Found");
		m.setMsg(id.getMsg());
		m.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(m,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(DataNotUpdate.class)
	public ResponseEntity<ResponseStructure<String> >dataNotUpdated(DataNotUpdate id){
		ResponseStructure<String> m = new ResponseStructure<String>();
		m.setData("Data not updated....Please check id");
		m.setMsg(id.getMsg());
		m.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(m,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ImageNotDelete.class)
	public ResponseEntity<ResponseStructure<String> >imageNotDelete(ImageNotDelete id){
		ResponseStructure<String> m = new ResponseStructure<String>();
		m.setData("Image not delete....Please check id");
		m.setMsg(id.getMsg());
		m.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(m,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(UserNotFound.class)
	public ResponseEntity<ResponseStructure<String> >userNotFound(UserNotFound id){
		ResponseStructure<String> m = new ResponseStructure<String>();
		m.setData("User not found.....");
		m.setMsg(id.getMsg());
		m.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(m,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(PostNotExist.class)
	public ResponseEntity<ResponseStructure<String> >postNotExist(PostNotExist id){
		ResponseStructure<String> m = new ResponseStructure<String>();
		m.setData("Post Not exist.....");
		m.setMsg(id.getMsg());
		m.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(m,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(DateInvalid.class)
	public ResponseEntity<ResponseStructure<String> >dateInvalid(DateInvalid id){
		ResponseStructure<String> m = new ResponseStructure<String>();
		m.setData("Data not save");
		m.setMsg(id.getMsg());
		m.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(m,HttpStatus.NOT_FOUND);
	}
	
//	@ResponseStatus(HttpStatus.BAD_REQUEST)
//	@ExceptionHandler(MethodArgumentNotValidException.class)
//	public Map<String, String> handleValidationExceptions(
//	  MethodArgumentNotValidException ex) {
//	    Map<String, String> errors = new HashMap<>();
//	    ex.getBindingResult().getAllErrors().forEach((error) -> {
//	        String fieldName = ((FieldError) error).getField();
//	        String errorMessage = error.getDefaultMessage();
//	        errors.put(fieldName, errorMessage);
//	    });
//	    return errors;
//	}
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
		org.springframework.http.HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		 java.util.List<ObjectError> error = ex.getAllErrors();
		Map<String, String> map = new HashMap<String, String>();
		ResponseStructure<Object> structure = new ResponseStructure<>();

		for (ObjectError objectError : error) {
			String filedName = ((FieldError) objectError).getField();
			String message = ((FieldError) objectError).getDefaultMessage();
			map.put(filedName, message);

		}
		structure.setMsg("provide valid details");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(map);

		return new ResponseEntity<Object>(structure, HttpStatus.BAD_REQUEST);
	}
	@org.springframework.web.bind.annotation.ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ResponseStructure<Object>> handleConstraintViolationException(ConstraintViolationException ex) {
		ResponseStructure<Object> structure = new ResponseStructure();
		Map<String, String> map = new HashMap<String, String>();

		for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
			String field = violation.getPropertyPath().toString();
			String message = violation.getMessage();
			map.put(field, message);

		}
		structure.setMsg("provide proper details");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(map);

		return new ResponseEntity<ResponseStructure<Object>>(structure, HttpStatus.BAD_REQUEST);

	}

}
