package acme.features.inventor.patronageReport;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronages.PatronageReport;
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
		PatronageReport result;
		result = new PatronageReport();
		final Date fecha = new Date();
		result.setCreation(fecha);
		return result;
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
