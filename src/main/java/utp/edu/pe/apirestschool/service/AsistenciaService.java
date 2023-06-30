package utp.edu.pe.apirestschool.service;

import java.util.List;

import utp.edu.pe.apirestschool.entity.Asistencia;

public interface AsistenciaService {
	public List<Asistencia> findByEstudiante(int idEstudiante);

	public Asistencia findByIdAsistencia(int idAsistencia);

	public Asistencia saveAsistencia(Asistencia asistencia);

	public Asistencia updateAsistencia(Asistencia asistencia);

	public void deleteAsistencia(int idAsistencia);	
}
