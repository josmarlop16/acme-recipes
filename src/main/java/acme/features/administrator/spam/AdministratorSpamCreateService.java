package acme.features.administrator.spam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import acme.entities.spam.Spam;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.roles.Administrator;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorSpamCreateService implements AbstractCreateService<Administrator, Spam> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AdministratorSpamRepository repository;

	@Override
	public boolean authorise(final Request<Spam> request) {
		assert request != null;
		boolean result;
		result = request.getPrincipal().hasRole(Administrator.class);
		return result;
	}

	@Override
	public Spam instantiate(final Request<Spam> request) {
		assert request != null;
		Spam result;
		result = new Spam();
		result.setSpamTerm("");
		result.setIsStrong(false);
		result.setThreshold(2);
		return result;
	}

	@Override
	public void bind(final Request<Spam> request, final Spam entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "spamTerm", "isStrong", "threshold", "confirm");
	}

	@Override
	public void validate(final Request<Spam> request, final Spam entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		boolean isConfirmed;

		isConfirmed = request.getModel().getBoolean("confirm");
		errors.state(request, isConfirmed, "confirm", "administrator.spam.form.error.must-confirm");
	}

	@Override
	public void unbind(final Request<Spam> request, final Spam entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "spamTerm", "isStrong", "threshold");
	}


	@Override
	public void create(final Request<Spam> request, final Spam entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}


}