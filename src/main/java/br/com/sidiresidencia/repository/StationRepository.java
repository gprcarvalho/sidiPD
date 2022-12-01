package br.com.sidiresidencia.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.sidiresidencia.model.Station;

public interface StationRepository extends JpaRepository<Station, Long>{
	
	@Query(value = "SELECT s FROM tb_station s WHERE NOT EXISTS "
		+ "(SELECT station.id FROM tb_reservation WHERE dateReservation = :date "
		+ "AND isCanceled = false)")List<Station> stationNotReservedByDate(LocalDate date);
	
	
}
