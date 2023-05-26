package utp.edu.pe.ApiRestSchool.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    /*Esta excepcion es lanzada cuando no se encuentra el recurso buscado
    puede aplicarse para los modulos de Gestion Profesor - Gestion Alumno
    y Gestion Asistencias, para los metodos getByID o para actualizar.
    * */

    private final String resourceName;
    private final String fieldName;

    private final Long fieldValue;

    public ResourceNotFoundException(String resourceName,String fieldName,Long fieldValue){
        super(String.format("%s not found with %s : '%s'",resourceName,fieldName,fieldValue));
        this.resourceName=resourceName;
        this.fieldName=fieldName;
        this.fieldValue=fieldValue;
    }
}
