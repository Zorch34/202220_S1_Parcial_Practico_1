package co.edu.uniandes.dse.parcialejemplo.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uniandes.dse.parcialejemplo.entities.ConciertoEntity;
import co.edu.uniandes.dse.parcialejemplo.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcialejemplo.repositories.ConciertoRepository;
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
	public ConciertoEntity createConcierto(ConciertoEntity conciertoEntity) throws IllegalOperationException {
		log.info("Inicia proceso de creación del concierto");

        if (!validateAsistentes(conciertoEntity.getAsistentes()))
			throw new IllegalOperationException("el numero de asistentes no es valido");

		log.info("Termina proceso de creación del concierto");
		return conciertoRepository.save(conciertoEntity);
    }
	private boolean validateAsistentes(Integer asistentes) {
	return !(asistentes > 50 || asistentes < 1000);
}
}

