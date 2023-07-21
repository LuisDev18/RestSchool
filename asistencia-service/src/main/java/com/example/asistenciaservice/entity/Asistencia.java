package com.example.asistenciaservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "asistencia")
@EntityListeners(AuditingEntityListener.class)
public class Asistencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_asistencia")
    private Integer idAsistencia;
    @Column(name = "id_clase")
    private Integer idClase;
    @Column(name = "id_estudiante")
    private Integer idEstudiante;
    @Column(name = "fecha_asistencia", nullable = false)
    private Date fechaAsistencia;
    private String observacion;
    private String auxiliar;
}
