package acme.features.chef.patronage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronages.Patronage;
import acme.entities.patronages.PatronageStatus;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Chef;

@Service
public class ChefPatronageDeniedService implements AbstractUpdateService<Chef, Patronage>{

	// Internal state ---------------------------------------------------------

	@Autowired
	protected ChefPatronageRepository repository;


	@Override
	public boolean authorise(final Request<Patronage> request) {
		assert request != null;
		
		boolean result;
		int masterId;
		Patronage patronage;
		Chef chef;

		masterId=request.getModel().getInteger("id");
		patronage=this.repository.findOnePatronageById(masterId);
		chef=patronage.getChef();
		result=patronage.getStatus() == PatronageStatus.PROPOSED && request.isPrincipal(chef);
		return result;
	}
	
	@Override
	public void validate(final Request<Patronage> request, final Patronage entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (!errors.hasErrors("code")) {
			final Patronage existingPatronage;
			existingPatronage=this.repository.findPatronageByCode(entity.getCode());
			errors.state(request, existingPatronage == null || existingPatronage.getId() == entity.getId(), "code", "chef.patronage.form.error.code-exists");
		}
		
	}
	
	
	@Override
	public void bind(final Request<Patronage> request, final Patronage entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors, "status", "code", "stuff", "budget", "creationMoment", "startDate", "endDate", "optionalLink",
				"patron.company", "patron.statement", "patron.optionalLink");
	}
	
	
	@Override
	public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "status", "code", "stuff", "budget", "creationMoment", "startDate", "endDate", "optionalLink",
				 "patron.company", "patron.statement", "patron.optionalLink");
	}

	
	@Override
	public Patronage findOne(final Request<Patronage> request) {
		assert request != null;

		Patronage result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOnePatronageById(id);

		return result;
	}
	
	
	@Override
	public void update(final Request<Patronage> request, final Patronage entity) {
		assert request != null;
		assert entity != null;
		
		entity.setStatus(PatronageStatus.DENIED);
		this.repository.save(entity);
		
	}
	
}
