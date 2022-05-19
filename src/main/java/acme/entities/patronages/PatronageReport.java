package acme.entities.patronages;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.entities.AbstractEntity;
import acme.roles.Inventor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class PatronageReport extends AbstractEntity{
	
	// Serialisation identifier -----------------------------------------------
	protected static final long serialVersionUID = 1L;
	
	// Attributes --------------------------------------------------------------
	
	@NotBlank
	@Pattern(regexp = "(^[A-Z]{3}-[0-9]{3}(-[A-Z])?):(?!0000)\\d{4}")
	protected String seqNumber;
	
	
	@Past
	@Temporal(TemporalType.DATE)
	protected Date creation;
	
	@NotBlank
	@Length(min = 0, max = 255)
	protected String memorandum;
	
	@URL
	protected String optionalLink;
	
	
	@NotNull
	@Valid
	@ManyToOne(optional = true) 
	protected Patronage patronage;
	
	@NotNull
	@Valid
	@ManyToOne(optional = false)
	protected Inventor inventor;
	
	
}
