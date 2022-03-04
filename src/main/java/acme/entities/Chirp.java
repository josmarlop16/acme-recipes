package acme.entities;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Length;

import acme.framework.entities.AbstractEntity;

public class Chirp extends AbstractEntity{
	
	// Serialisation identifier -----------------------------------------------
	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------
	@Past
	@Temporal(TemporalType.TIMESTAMP)
	protected Date creationMoment;
	
	@NotBlank
	@Length(max = 100)
	protected String title;
	
	@NotBlank
	@Length(max = 100)
	protected String author;
	
	@NotBlank
	@Length(max = 255)
	protected String body;
	
	@Email
	protected String emailAddress;

}
