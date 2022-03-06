package acme.roles;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.roles.UserRole;

public class Patron extends UserRole{
	
	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@NotBlank
	@Length(min = 0, max = 100)
	protected String company;

	@NotBlank
	@Length(min = 0, max = 255)
	protected String statement;

	@URL
	protected String optionalLink;

}
