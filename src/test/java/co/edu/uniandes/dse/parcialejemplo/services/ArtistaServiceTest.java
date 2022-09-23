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

import co.edu.uniandes.dse.parcialejemplo.entities.ArtistaEntity;
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
@Import(ArtistaService.class)
class ArtistaServiceTest {

	@Autowired
	private ArtistaService artistaService;

	@Autowired
	private TestEntityManager entityManager;

	private PodamFactory factory = new PodamFactoryImpl();

	private List<ArtistaEntity> artistaList = new ArrayList<>();

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
		entityManager.getEntityManager().createQuery("delete from ArtistaEntity").executeUpdate();
	}

	/**
	 * Inserta los datos iniciales para el correcto funcionamiento de las pruebas.
	 */
	private void insertData() {
		for (int i = 0; i < 3; i++) {
			ArtistaEntity artista = factory.manufacturePojo(ArtistaEntity.class);
			entityManager.persist(artista);
			artistaList.add(artista);
		}
	}

	/**
	 * Prueba para crear un Artista
	 *
	 * @throws EntityNotFoundException, IllegalOperationException
	 */
	@Test
	void testCreateArtista() throws EntityNotFoundException, IllegalOperationException {
		ArtistaEntity newEntity = factory.manufacturePojo(ArtistaEntity.class);
		ArtistaEntity result = artistaService.createArtista(newEntity);
		assertNotNull(result);

		ArtistaEntity entity = entityManager.find(ArtistaEntity.class, result.getId());
		assertEquals(newEntity.getId(), entity.getId());
		assertEquals(newEntity.getNombre(), entity.getNombre());
	}
	/**
	 * Prueba para crear un Artista con el mismo nombre de un Artista que ya
	 * existe.
	 *
	 * @throws IllegalOperationException
	 */
	@Test
	void testCreateArtistaWithSameName() {
		assertThrows(IllegalOperationException.class, () -> {
			ArtistaEntity newEntity = factory.manufacturePojo(ArtistaEntity.class);
			newEntity.setNombre(artistaList.get(0).getNombre());
			artistaService.createArtista(newEntity);
		});
	}
}

	