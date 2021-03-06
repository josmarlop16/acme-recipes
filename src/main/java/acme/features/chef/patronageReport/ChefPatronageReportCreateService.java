package acme.features.chef.patronageReport;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.SpamModule;
import acme.entities.patronages.Patronage;
import acme.entities.patronages.PatronageReport;
import acme.features.administrator.spam.AdministratorSpamRepository;
import acme.features.chef.patronage.ChefPatronageRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Chef;

@Service
public class ChefPatronageReportCreateService implements AbstractCreateService<Chef, PatronageReport> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected ChefPatronageReportRepository repository;
	
	@Autowired
	protected ChefPatronageRepository patronageRepository;
	
	@Autowired
	protected AdministratorSpamRepository spamRepository;

	@Override
	public boolean authorise(final Request<PatronageReport> request) {
		assert request != null;
		boolean result;
		result = request.getPrincipal().hasRole(Chef.class);
		return result;
	}
	
	@Override
	public PatronageReport instantiate(final Request<PatronageReport> request) {
		assert request != null;
		
		final PatronageReport patronageReport = new PatronageReport();
		final Chef chef = this.repository.findChefById(request.getPrincipal().getActiveRoleId());
		final Date fecha = new Date();
		
		patronageReport.setCreation(fecha);
		patronageReport.setMemorandum("");
		patronageReport.setChef(chef);
		patronageReport.setOptionalLink("");
		patronageReport.setSeqNumber("");
		
		return patronageReport;
	}

	@Override
	public void bind(final Request<PatronageReport> request, final PatronageReport entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		entity.setPatronage(this.patronageRepository.findOnePatronageById(Integer.valueOf(request.getModel().getAttribute("patronageId").toString())));
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
		errors.state(request, isConfirmed, "confirm", "chef.patronageReport.form.error.must-confirm");
	}

	@Override
	public void unbind(final Request<PatronageReport> request, final PatronageReport entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		final Integer chefId = request.getPrincipal().getActiveRoleId();
		final List<Patronage> myPatronages= (List<Patronage>) this.patronageRepository.findAllPatronagesByChefId(chefId);
		model.setAttribute("myPatronages", myPatronages);

		request.unbind(entity, model, "seqNumber", "creation", "memorandum", "optionalLink");
	}


	@Override
	public void create(final Request<PatronageReport> request, final PatronageReport entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

	
}
