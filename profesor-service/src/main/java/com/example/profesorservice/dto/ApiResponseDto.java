package com.example.profesorservice.dto;

import lombok.Data;


import java.util.List;

@Data
public class ApiResponseDto {

    private List<AlumnoDto> alumnoDto;
    private ProfesorDto profesorDto;
}
