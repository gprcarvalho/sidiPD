package br.com.sidiresidencia.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.sidiresidencia.model.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long>{
	
	@Query(value =  "SELECT r FROM tb_reservation r WHERE r.createdBy = :idUser AND r.isCanceled = false")
	List<Reservation> findAllActiveReservationByUser(Long idUser); 
	
	@Query(value = "SELECT r FROM tb_reservation r WHERE r.dateReservation = :date AND r.station.id = :id AND r.isCanceled = false") 
	Reservation findByReservation(LocalDate date, Long id);  
	
	@Query(value = "SELECT r FROM tb_reservation r WHERE r.createdBy.id = :id AND r.dateReservation = :date AND r.isCanceled = false")
	Reservation checkExistReservationUserDate(Long id, LocalDate date);
	
	@Query(value = "SELECT r FROM tb_reservation r WHERE r.id <> :idReservation AND r.createdBy.id = :id AND r.dateReservation = :date AND r.isCanceled = false")
	Reservation checkExistReservationUserDateUpdate(Long idReservation, Long id, LocalDate date);
}
