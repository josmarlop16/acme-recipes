package acme.features.inventor.patronage;

import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import acme.roles.Inventor;
import acme.entities.patronages.Patronage;

@Service
public class InventorPatronageListByInventorService implements AbstractListService<Inventor, Patronage>{
	
	// Internal state ---------------------------------------------------------

	@Autowired
	protected InventorPatronageRepository repository;
	
	@Override
	public boolean authorise(final Request<Patronage> request) {
		assert request != null;
		
		return true;
	}

	@Override
	public Collection<Patronage> findMany(final Request<Patronage> request) {
		assert request != null;
		
		Collection<Patronage> result;
		
		Principal principal;

		principal = request.getPrincipal();		
		result = this.repository.findAllPatronagesByInventorId(principal.getActiveRoleId());
		
		return result;
	}

	@Override
	public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "status", "code", "stuff", "budget", "periodOfTime", "optionalLink");
		
	}
}
