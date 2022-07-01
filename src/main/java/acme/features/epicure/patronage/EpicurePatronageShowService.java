package acme.features.epicure.patronage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronages.Patronage;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Epicure;

@Service
public class EpicurePatronageShowService implements AbstractShowService<Epicure, Patronage>{

	// Internal state ---------------------------------------------------------

	@Autowired
	protected EpicurePatronageRepository repository;


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

		model.setAttribute("chefs", this.repository.findAllChefs());
		model.setAttribute("inventId", entity.getChef().getId());
		model.setAttribute("chefUsername", entity.getChef().getUserAccount().getUsername());
		request.unbind(entity, model, "status", "code", "stuff", "budget","creationMoment","startDate","endDate", "optionalLink","published",
				 "chef.company", "chef.statement", "chef.optionalLink");
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
