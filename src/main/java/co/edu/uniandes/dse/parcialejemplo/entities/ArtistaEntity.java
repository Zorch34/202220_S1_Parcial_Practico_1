package co.edu.uniandes.dse.parcialejemplo.entities;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;
import uk.co.jemos.podam.common.PodamExclude;

/**
 * Clase que representa un autor en la persistencia
 *
 * @author ISIS2603
 */

@Getter
@Setter
@Entity
public class ArtistaEntity extends BaseEntity{
    private String nombre;
	private String descripccion;
    private String pagina;

    @PodamExclude
	@ManyToOne
	private ConciertoEntity concierto;
}