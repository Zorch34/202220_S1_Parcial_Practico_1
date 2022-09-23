package co.edu.uniandes.dse.parcialejemplo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.uniandes.dse.parcialejemplo.entities.ConciertoEntity;
/**
 * Interface that persists an organization
 *
 * @author ISIS2603
 *
 */
@Repository
public interface ConciertoRepository extends JpaRepository<ConciertoEntity, Long> {
	List<ConciertoEntity> findByNombre(String nombre);
}