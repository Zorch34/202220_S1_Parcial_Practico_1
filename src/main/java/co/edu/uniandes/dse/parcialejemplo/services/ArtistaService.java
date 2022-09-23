package co.edu.uniandes.dse.parcialejemplo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uniandes.dse.parcialejemplo.entities.ArtistaEntity;

import co.edu.uniandes.dse.parcialejemplo.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcialejemplo.repositories.ArtistaRepository;
import lombok.extern.slf4j.Slf4j;

/**
 * Clase que implementa la conexion con la persistencia para la entidad de
 * Author.
 *
 * @author ISIS2603
 */

@Slf4j
@Service
public class ArtistaService {

	@Autowired
	ArtistaRepository artistaRepository;

	@Transactional
	public ArtistaEntity createArtista(ArtistaEntity artistaEntity) throws IllegalOperationException {
		log.info("Inicia proceso de creación del artista");
        if (!artistaRepository.findByNombre(artistaEntity.getNombre()).isEmpty()) {
			throw new IllegalOperationException("artista name already exists");
	    }
		log.info("Termina proceso de creación del artista");
		return artistaRepository.save(artistaEntity);
	}
}
