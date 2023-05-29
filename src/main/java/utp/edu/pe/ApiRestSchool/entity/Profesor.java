package utp.edu.pe.ApiRestSchool.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import lombok.Data;

@Entity
@Data
public class Profesor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    
    private String nombre;

    
    private String apellidoPaterno;

    
    private String apellidoMaterno;

    
    private String dni;

    
    private String celular;

   
    private String email;

    
    private String curso;
}
