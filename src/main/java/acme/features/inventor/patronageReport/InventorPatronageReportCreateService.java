package acme.features.inventor.patronageReport;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.SpamModule;
import acme.entities.patronages.Patronage;
import acme.entities.patronages.PatronageReport;
import acme.features.administrator.spam.AdministratorSpamRepository;
import acme.features.inventor.patronage.InventorPatronageRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;

@Service
public class InventorPatronageReportCreateService implements AbstractCreateService<Inventor, PatronageReport> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected InventorPatronageReportRepository repository;
	
	@Autowired
	protected InventorPatronageRepository patronageRepository;
	
	@Autowired
	protected AdministratorSpamRepository spamRepository;

	@Override
	public boolean authorise(final Request<PatronageReport> request) {
		assert request != null;
		boolean result;
		result = request.getPrincipal().hasRole(Inventor.class);
		return result;
	}
	
	@Override
	public PatronageReport instantiate(final Request<PatronageReport> request) {
		assert request != null;
		
		final PatronageReport patronageReport = new PatronageReport();
		final Inventor inventor = this.repository.findInventorById(request.getPrincipal().getActiveRoleId());
		final Patronage patronage = this.patronageRepository.findOnePatronageById(9);
		final Date fecha = new Date();
		
		patronageReport.setCreation(fecha);
		patronageReport.setMemorandum("");
		patronageReport.setInventor(inventor);
		patronageReport.setOptionalLink("");
		patronageReport.setPatronage(patronage);
		patronageReport.setSeqNumber("");
		
		return patronageReport;
	}

	@Override
	public void bind(final Request<PatronageReport> request, final PatronageReport entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "seqNumber", "creation", "memorandum", "optionalLink", "confirm");
	}
	
	@Override
	public void validate(final Request<PatronageReport> request, final PatronageReport entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		boolean isConfirmed;
		
		if (!errors.hasErrors("seqNumber")) {
            errors.state(request, SpamModule.spamValidator(entity.getSeqNumber(), this.spamRepository.findWeakSpamsWords(), this.spamRepository.findStrongSpamsWords()), "seqNumber", "form.error.spam");
        }
		
		if (!errors.hasErrors("memorandum")) {
            errors.state(request, SpamModule.spamValidator(entity.getMemorandum(), this.spamRepository.findWeakSpamsWords(), this.spamRepository.findStrongSpamsWords()), "memorandum", "form.error.spam");
        }
		
		if (!errors.hasErrors("optionalLink")) {
            errors.state(request, SpamModule.spamValidator(entity.getOptionalLink(), this.spamRepository.findWeakSpamsWords(), this.spamRepository.findStrongSpamsWords()), "optionalLink", "form.error.spam");
        }
		
		isConfirmed = request.getModel().getBoolean("confirm");
		errors.state(request, isConfirmed, "confirm", "inventor.patronageReport.form.error.must-confirm");
	}

	@Override
	public void unbind(final Request<PatronageReport> request, final PatronageReport entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "seqNumber", "creation", "memorandum", "optionalLink");
	}


	@Override
	public void create(final Request<PatronageReport> request, final PatronageReport entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

	
}
