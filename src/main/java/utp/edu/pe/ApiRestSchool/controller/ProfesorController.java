package utp.edu.pe.ApiRestSchool.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(
        name="CRUD REST API for User Resource",
        description = "CRUD REST API - Create User, Update User, Get User, Get all User, Delete User"
)
@RestController
@RequestMapping(value="api/v1/profesor")
public class ProfesorController {

}
