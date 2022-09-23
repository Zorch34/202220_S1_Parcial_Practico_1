package co.edu.uniandes.dse.parcialejemplo.services;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import co.edu.uniandes.dse.parcialejemplo.entities.ConciertoEntity;
import co.edu.uniandes.dse.parcialejemplo.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.parcialejemplo.exceptions.IllegalOperationException;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 * Pruebas de logica de Reviews
 *
 * @author ISIS2603
 */
@ExtendWith(SpringExtension.class)
@DataJpaTest
@Transactional
@Import(ConciertoService.class)
class ConciertoServiceTest {

	@Autowired
	private ConciertoService conciertoService;

	@Autowired
	private TestEntityManager entityManager;

	private PodamFactory factory = new PodamFactoryImpl();

	private List<ConciertoEntity> conciertoList = new ArrayList<>();

	/**
	 * Configuración inicial de la prueba.
	 */
	@BeforeEach
	void setUp() {
		clearData();
		insertData();
	}

	/**
	 * Limpia las tablas que están implicadas en la prueba.
	 */
	private void clearData() {
		entityManager.getEntityManager().createQuery("delete from ConciertoEntity").executeUpdate();
	}

	/**
	 * Inserta los datos iniciales para el correcto funcionamiento de las pruebas.
	 */
	private void insertData() {
		for (int i = 0; i < 3; i++) {
			ConciertoEntity concierto = factory.manufacturePojo(ConciertoEntity.class);
			entityManager.persist(concierto);
			conciertoList.add(concierto);
		}
	}

	/**
	 * Prueba para crear un Artista
	 *
	 * @throws EntityNotFoundException, IllegalOperationException
	 */
	@Test
	void testCreateConcierto() throws EntityNotFoundException, IllegalOperationException {
		ConciertoEntity newEntity = factory.manufacturePojo(ConciertoEntity.class);
		ConciertoEntity result = conciertoService.createConcierto(newEntity);
		assertNotNull(result);

		ConciertoEntity entity = entityManager.find(ConciertoEntity.class, result.getId());
		assertEquals(newEntity.getId(), entity.getId());
		assertEquals(newEntity.getNombre(), entity.getNombre());
	}
	/**
	 * Prueba para crear un Concierto con el mismo nombre de un Concierto que ya
	 * existe.
	 *
	 * @throws IllegalOperationException
	 */
	@Test
	void testCreateConciertoWithSameName() {
		assertThrows(IllegalOperationException.class, () -> {
			ConciertoEntity newEntity = factory.manufacturePojo(ConciertoEntity.class);
			newEntity.setNombre(conciertoList.get(0).getNombre());
			conciertoService.createConcierto(newEntity);
		});
	}
}
