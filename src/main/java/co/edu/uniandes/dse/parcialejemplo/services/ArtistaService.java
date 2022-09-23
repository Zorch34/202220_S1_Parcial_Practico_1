package co.edu.uniandes.dse.parcialejemplo.services;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uniandes.dse.parcialejemplo.entities.ArtistaEntity;
import co.edu.uniandes.dse.bookstore.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.bookstore.exceptions.ErrorMessage;
import co.edu.uniandes.dse.bookstore.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcialejemplo.entities.ConciertoEntity;
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
	public ArtistaEntity createArtista(ArtistaEntity artista) throws IllegalOperationException {
		log.info("Inicia proceso de creación del artista");
        if (!ArtistaRepository.findByName(ArtistaEntity.getName()).isEmpty()) {
			throw new IllegalOperationException("artista name already exists");
	    }
		log.info("Termina proceso de creación del artista");
		return editorialRepository.save(artistaEntity);
	}
}
