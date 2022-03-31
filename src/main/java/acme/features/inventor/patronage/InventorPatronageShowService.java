package acme.features.inventor.patronage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import acme.roles.Inventor;
import acme.entities.patronages.Patronage;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;

@Service
public class InventorPatronageShowService implements AbstractShowService<Inventor, Patronage>{

	// Internal state ---------------------------------------------------------

	@Autowired
	protected InventorPatronageRepository repository;


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
		
		request.unbind(entity, model, "status", "code", "stuff", "budget", "periodOfTime", "optionalLink",
				"patron.name", "patron.company", "patron.statement", "patron.optionalLink");
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
