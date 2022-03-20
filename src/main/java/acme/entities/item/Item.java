package acme.entities.item;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.datatypes.Money;
import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Item extends AbstractEntity{

	private static final long serialVersionUID = 1L;

	@NotBlank
	@Length(min = 0, max = 100)
	protected String name;

	@Column(unique=true)
	@Pattern(regexp="^[A-Z]{3}-[0-9]{3}(-[A-Z])?$")
	protected String code;

	@NotBlank
	@Length(min = 0, max = 100)
	protected String technology;

	@NotBlank
	@Length(min = 0, max = 255)
	protected String description;
	
	protected Money retailPrice;
	
	@URL
	protected String link;
	
	@NotNull
	protected ItemType type;
	
}