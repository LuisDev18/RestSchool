package utp.edu.pe.ApiRestSchool.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(
        name="CRUD REST API para el modulo Profesor ",
        description = "CRUD REST API - Create Profesor, Update Profesor, Get Profesor, Get all Profesor, Delete Profesor"
)
@RestController
@RequestMapping(value="api/v1/profesor")
public class ProfesorController {

}
