package acme.entities.patronages;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.GeneratedValue;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class PatronageReport extends AbstractEntity{
	
	// Serialisation identifier -----------------------------------------------
	protected static final long serialVersionUID = 1L;
	
	// Attributes --------------------------------------------------------------

	@Past
	@Temporal(TemporalType.DATE)
	protected Date creation;
	
	@NotBlank
	@Length(min = 0, max = 255)
	protected String memorandum;
	
	@URL
	protected String optionalLink;
	
	// Derived attributes -----------------------------------------------------
	
	@Column(unique = true)
	@GeneratedValue(generator = "seqNumber")
	protected String seqNumber;
	
	@GenericGenerator(name = "seqNumber", strategy = "native")
	protected String getSeqNumber() {
		String patronageCode = this.patronage.code;
		String serialNumber = getSerialNumber();
		return patronageCode + ":" + serialNumber;
	}
	
	@Transient
	protected String getSerialNumber() {
		Integer reportId = this.id;
		Integer nZeros = 4 - reportId.toString().length();
		String serialNumber = new String(new char[nZeros]).replace("\0", "0");
		serialNumber += reportId;
		return serialNumber;
	}
	
	// Relationships ----------------------------------------------------------
	
	@NotNull
	@Valid
//	@OneToOne(optional = false)
	protected Patronage patronage;
	
	
}
