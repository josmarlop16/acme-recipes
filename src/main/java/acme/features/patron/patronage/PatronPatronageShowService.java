package acme.features.patron.patronage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronages.Patronage;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Patron;

@Service
public class PatronPatronageShowService implements AbstractShowService<Patron, Patronage>{

	// Internal state ---------------------------------------------------------

	@Autowired
	protected PatronPatronageRepository repository;


	@Override
	public boolean authorise(final Request<Patronage> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		model.setAttribute("inventors", this.repository.findAllInventors());
		model.setAttribute("inventId", entity.getInventor().getId());
		model.setAttribute("inventorUsername", entity.getInventor().getUserAccount().getUsername());
		request.unbind(entity, model, "status", "code", "stuff", "budget", "periodOfTime", "optionalLink","published",
				 "inventor.company", "inventor.statement", "inventor.optionalLink");
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
	
}
