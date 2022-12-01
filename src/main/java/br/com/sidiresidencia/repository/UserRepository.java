package br.com.sidiresidencia.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.sidiresidencia.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	Optional<User> findByEmail(String email);
	
	@Query(value = "SELECT u FROM tb_user u WHERE u.id = :id") 
	User findByUser(Long id);
}
