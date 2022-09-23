package main.java.co.edu.uniandes.dse.parcialejemplo.services;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import main.java.co.edu.uniandes.dse.parcialejemplo.entities;
import co.edu.uniandes.dse.bookstore.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.bookstore.exceptions.ErrorMessage;
import co.edu.uniandes.dse.bookstore.exceptions.IllegalOperationException;
import main.java.co.edu.uniandes.dse.parcialejemplo.repositories;
import lombok.extern.slf4j.Slf4j;

/**
 * Clase que implementa la conexion con la persistencia para la entidad de
 * Author.
 *
 * @author ISIS2603
 */

@Slf4j
@Service
public class ConciertoService {

	@Autowired
	ConciertoRepository conciertoRepository;
	

	@Transactional
	public ConciertoEntity createConcierto(ConciertoEntity concierto) throws IllegalOperationException {
		log.info("Inicia proceso de creación del concierto");
        Optional<ConciertoEntity> conciertoEntity = editorialRepository.findById(editorialId);
        if (editorialEntity.isEmpty())
            throw new EntityNotFoundException(ErrorMessage.EDITORIAL_NOT_FOUND);

        if (!validateAsistentes(bookEntity.getasistentes()))
			throw new IllegalOperationException("el numero de asistentes no es valido");

		log.info("Termina proceso de creación del concierto");
		return conciertorepository.save(conciertoEntity);
    }
        private boolean validateAsistentes(Integer asistentes) {
            return !(asistentes > 50 || asistentes < 1000);
	}
    
}
