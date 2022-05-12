package acme.features.administrator.announcement;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.roles.Administrator;
import acme.framework.services.AbstractCreateService;
import acme.entities.announcement.Announcement;
import org.springframework.stereotype.Service;

@Service
public class AdministratorAnnouncementCreateService implements AbstractCreateService<Administrator, Announcement> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AdministratorAnnouncementRepository repository;

	// AbstractCreateService<Administrator, Announcement> interface ---------------


	@Override
	public boolean authorise(final Request<Announcement> request) {
		assert request != null;
		boolean result;
		result = request.getPrincipal().hasRole(Administrator.class);
		return result;
	}
	
	@Override
	public Announcement instantiate(final Request<Announcement> request) {
		assert request != null;
		Announcement result;
		result = new Announcement();
		Date fecha = new Date();
		result.setCreation(fecha);
		result.setCritical(false);
		return result;
	}

	@Override
	public void bind(final Request<Announcement> request, final Announcement entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "title", "critical", "body", "optionalLink", "confirm");
	}
	
	@Override
	public void validate(final Request<Announcement> request, final Announcement entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		boolean isConfirmed;
		
		isConfirmed = request.getModel().getBoolean("confirm");
		errors.state(request, isConfirmed, "confirm", "administrator.announcement.form.error.must-confirm");
	}

	@Override
	public void unbind(final Request<Announcement> request, final Announcement entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "critical", "body", "optionalLink");
	}


	@Override
	public void create(final Request<Announcement> request, final Announcement entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

	
}
