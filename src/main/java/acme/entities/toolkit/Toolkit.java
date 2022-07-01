package acme.entities.toolkit;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.entities.AbstractEntity;
import acme.roles.Chef;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Toolkit extends AbstractEntity {

	// Serialisation identifier -----------------------------------------------
	
	protected static final long	serialVersionUID = 1L;

	// Attributes -------------------------------------------------------------
	
	@NotBlank
	@Length(min = 0, max = 100)
	protected String title;
	
	@Column(unique = true)
	@Pattern(regexp = "^[A-Z]{3}-[0-9]{3}(-[A-Z])?$")
	protected String code;
	
	@NotBlank
	@Length(min = 0, max = 255)
	protected String description;
	
	@NotBlank
	@Length(min = 0, max = 255)
	protected String assemblyNotes;
	
	@URL
	protected String link;
	
	@NotNull
	protected Boolean published;
	
	// Relationships ----------------------------------------------------------
	
	@NotNull
	@Valid
	@ManyToOne(optional = false)
	protected Chef chef;

}