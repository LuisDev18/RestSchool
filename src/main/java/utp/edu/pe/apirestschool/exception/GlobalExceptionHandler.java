package utp.edu.pe.apirestschool.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<CustomErrorResponse> handlerResourceNotFoundException(
      ResourceNotFoundException exception, WebRequest webRequest) {
    CustomErrorResponse errorDetails =
        new CustomErrorResponse(
            LocalDateTime.now(),
            exception.getMessage(),
            webRequest.getDescription(false),
            "RESOURCE_NOT_FOUND");
    return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(EmailAlreadyExistsException.class)
  public ResponseEntity<CustomErrorResponse> handleEmailAlreadyExistsException(
      EmailAlreadyExistsException exception, WebRequest webRequest) {
    CustomErrorResponse errorDetails =
        new CustomErrorResponse(
            LocalDateTime.now(),
            exception.getMessage(),
            webRequest.getDescription(false),
            "EMAIL_ALREADY_EXISTS");
    return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<CustomErrorResponse> handleGlobalException(
      Exception exception, WebRequest webRequest) {
    CustomErrorResponse errorDetails =
        new CustomErrorResponse(
            LocalDateTime.now(),
            exception.getMessage(),
            webRequest.getDescription(false),
            "INTERNAL_SERVER_ERROR");
    return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  /*Personalizando el response del metodo handleMethodArgumentNotValid para el response en caso
   * de ingresar un valor invalido.
   */

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException ex,
      HttpHeaders headers,
      HttpStatusCode status,
      WebRequest request) {

    // almacenamos multiples mensajes de error en un objeto Map<E,T>
    Map<String, String> errors = new HashMap<>();
    List<ObjectError> errorList = ex.getBindingResult().getAllErrors();

    errorList.forEach(
        (error) -> {
          String fieldName = ((FieldError) error).getField();
          String message = error.getDefaultMessage();
          errors.put(fieldName, message);
        });

    return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
  }
}
