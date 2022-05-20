package acme.features.administrator.announcement;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.SpamModule;
import acme.entities.announcement.Announcement;
import acme.features.administrator.spam.AdministratorSpamRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.roles.Administrator;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorAnnouncementCreateService implements AbstractCreateService<Administrator, Announcement> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AdministratorAnnouncementRepository repository;

	@Autowired
	protected AdministratorSpamRepository spamRepository;


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
		final Date fecha = new Date();
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
		
		if (!errors.hasErrors("title")) {
            errors.state(request, SpamModule.spamValidator(entity.getTitle(), this.spamRepository.findWeakSpamsWords(), this.spamRepository.findStrongSpamsWords()), "title", "form.error.spam");
        }
		
		if (!errors.hasErrors("optionalLink")) {
            errors.state(request, SpamModule.spamValidator(entity.getOptionalLink(), this.spamRepository.findWeakSpamsWords(), this.spamRepository.findStrongSpamsWords()), "optionalLink", "form.error.spam");
        }
		
		if (!errors.hasErrors("body")) {
            errors.state(request, SpamModule.spamValidator(entity.getBody(), this.spamRepository.findWeakSpamsWords(), this.spamRepository.findStrongSpamsWords()), "body", "form.error.spam");
        }
		
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
