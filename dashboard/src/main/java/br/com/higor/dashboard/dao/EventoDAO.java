package br.com.higor.dashboard.dao;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.higor.dashboard.dto.VolumeAlarmes;
import br.com.higor.dashboard.model.Evento;

public interface EventoDAO extends CrudRepository<Evento, Integer> {
	public ArrayList<Evento> findAllByDataBetween(LocalDate inicio, LocalDate fim);
	
	@Query("SELECT new br.com.higor.dashboard.dto.VolumeAlarmes(evt.alarme.nome, count(evt.alarme.id)) "
			+ " FROM Evento evt GROUP BY evt.alarme.id ")
	public ArrayList<VolumeAlarmes> getAllVolumes();
	
	@Query("SELECT new br.com.higor.dashboard.dto.VolumeAlarmes(evt.alarme.nome, count(evt.alarme.id)) "
			+ " FROM Evento evt "
			+ " WHERE evt.data >= :dataInicio AND evt.data <= :dataFim "
			+ "GROUP BY evt.alarme.id ")
	public ArrayList<VolumeAlarmes> getAllByDatas(@Param ("dataInicio") LocalDate dataInicio, @Param ("dataFim") LocalDate dataFim);
}
