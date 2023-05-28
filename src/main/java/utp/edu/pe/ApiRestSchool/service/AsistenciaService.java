package utp.edu.pe.ApiRestSchool.service;

import java.util.List;

import utp.edu.pe.ApiRestSchool.entity.Asistencia;

public interface AsistenciaService {
	public List<Asistencia> findByEstudiante(int idEstudiante);

	public Asistencia findByIdAsistencia(int idAsistencia);

	public Asistencia saveAsistencia(Asistencia asistencia);

	public Asistencia updateAsistencia(Asistencia asistencia);

	public void deleteAsistencia(int idAsistencia);	
}
