package com.example.profesorservice.entity;

import com.example.profesorservice.dto.AlumnoDto;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.List;

@Data
@Entity
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
