package co.edu.uniandes.dse.parcialejemplo.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import co.edu.uniandes.dse.parcialejemplo.podam.DateStrategy;
import lombok.Getter;
import lombok.Setter;
import uk.co.jemos.podam.common.PodamExclude;
import uk.co.jemos.podam.common.PodamStrategyValue;

/**
 * Clase que representa un autor en la persistencia
 *
 * @author ISIS2603
 */

@Getter
@Setter
@Entity
public class ConciertoEntity extends BaseEntity{
    private String nombre;
	private String descripcion;
	@Temporal(TemporalType.DATE)
	@PodamStrategyValue(DateStrategy.class)
	private Date fecha;
    private String pais;
	private Integer asistentes;
	private String ciudad;

    @PodamExclude
    @OneToMany(mappedBy = "concierto")
	private List<ArtistaEntity> Artistas = new ArrayList<>();
}