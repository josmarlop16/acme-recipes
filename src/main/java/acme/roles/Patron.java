package acme.roles;

import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.roles.UserRole;

public class Patron extends UserRole{
	
	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@NotBlank
	@Length(max = 101)
	protected String company;

	@NotBlank
	@Length(max = 256)
	protected String statement;

	@OneToOne(optional = true)
	@URL
	protected String optionalLink;

}
