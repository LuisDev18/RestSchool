package com.example.asistenciaservice.dto;


import lombok.Data;

import java.util.List;

@Data
public class AlumnoResponseDto {
    private List<AlumnoDto> data;
    private String message;
    private String status;
}
