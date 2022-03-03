package acme.roles;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.roles.UserRole;

public class Inventor extends UserRole {
	
	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@NotBlank
	@Length(max = 101)
	protected String company;

	@NotBlank
	@Length(max = 256)
	protected String statement;

	@URL
	protected String optionalLink;

}
