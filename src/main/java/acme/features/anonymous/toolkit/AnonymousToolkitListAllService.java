package acme.features.anonymous.toolkit;
import java.util.Collection;

import org.springframework.stereotype.Service;

import acme.entities.item.Item;
import acme.entities.toolkit.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Anonymous;
import acme.framework.services.AbstractListService;
import org.springframework.beans.factory.annotation.Autowired;


@Service
public class AnonymousToolkitListAllService implements AbstractListService<Anonymous, Toolkit>{

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AnonymousToolkitRepository repository;
	
	@Override
	public boolean authorise(final Request<Toolkit> request) {
		assert request != null;
		
		return true;
	}

	@Override
	public Collection<Toolkit> findMany(final Request<Toolkit> request) {
		assert request != null;
		
		Collection<Toolkit> result;
		
		result = this.repository.findAllToolkits();
		
		return result;
	}

	@Override
	public void unbind(final Request<Toolkit> request, final Toolkit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "title", "code", "description", "assemblyNotes", "link", "item.name", "item.id");
		
	}

}
