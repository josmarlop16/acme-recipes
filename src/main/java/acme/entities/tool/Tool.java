package acme.entities.tool;

import javax.persistence.Entity;

import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Tool extends AbstractEntity {

	// Serialisation identifier -----------------------------------------------
	protected static final long	serialVersionUID	= 1L;

}