package acme.entities.component;

import javax.persistence.Entity;

import acme.entities.item.Item;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Component extends Item {

	protected static final long	serialVersionUID	= 1L;

}