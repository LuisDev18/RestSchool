package utp.edu.pe.ApiRestSchool.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST)
public class EmailAlreadyExistsException extends RuntimeException{

    /*Esta excepcion es lanzada cuando se intenta registrar un email, que
    ya ha sido usado en el proceso de registro por otro usuario y se encuentra
    almanecenado en la base de datos.
    * */
    private String message;

    public EmailAlreadyExistsException(String message){

        super(message);
    }
}
