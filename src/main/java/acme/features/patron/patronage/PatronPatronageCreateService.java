package acme.features.patron.patronage;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronages.Patronage;
import acme.entities.patronages.PatronageStatus;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;
import acme.roles.Patron;

@Service
public class PatronPatronageCreateService implements AbstractCreateService<Patron, Patronage> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected PatronPatronageRepository repository;

	// AbstractCreateService<Any, Chirp> interface ---------------


	@Override
	public boolean authorise(final Request<Patronage> request) {
		assert request != null;
		boolean result;
		result = request.getPrincipal().hasRole(Patron.class);
		return result;
	}
	
	@Override
	public Patronage instantiate(final Request<Patronage> request) {
		assert request != null;
		
		Patron p = this.repository.findPatronById(request.getPrincipal().getActiveRoleId());
		
		Patronage result;
		Date fecha;
		fecha = new Date(System.currentTimeMillis() - 1);
		result = new Patronage();
		result.setPeriodOfTime(fecha);
		result.setStatus(PatronageStatus.PROPOSED);
		result.setPatron(p);
		
		return result;
	}

	@Override
	public void bind(final Request<Patronage> request, final Patronage entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		entity.setInventor(this.repository.findInventorById(Integer.valueOf(request.getModel().getAttribute("inventorId").toString())).orElse(null));
		request.bind(entity, errors, "status", "code", "stuff", "budget", "periodOfTime", "optionalLink", "published");
	}
	
	@Override
	public void validate(final Request<Patronage> request, final Patronage entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		List<Inventor> lsInventors= this.repository.findAllInventors();
		model.setAttribute("inventors", lsInventors);
		request.unbind(entity, model, "status", "code", "stuff", "budget", "periodOfTime", "optionalLink", "published");
	}


	@Override
	public void create(final Request<Patronage> request, final Patronage entity) {
		assert request != null;
		assert entity != null;
		this.repository.save(entity);
	}
	

	
}