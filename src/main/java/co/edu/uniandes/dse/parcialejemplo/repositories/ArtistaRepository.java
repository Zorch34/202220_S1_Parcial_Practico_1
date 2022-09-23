package co.edu.uniandes.dse.parcialejemplo.repositories;

import co.edu.uniandes.dse.parcialejemplo.entities.ArtistaEntity;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ArtistaRepository extends JpaRepository<ArtistaEntity, Long> {
	List<ArtistaEntity> findByNombre(String nombre);
}