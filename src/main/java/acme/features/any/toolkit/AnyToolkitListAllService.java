package acme.features.any.toolkit;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.item.Item;
import acme.entities.toolkit.Toolkit;
import acme.features.any.item.AnyItemRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractListService;


@Service
public class AnyToolkitListAllService implements AbstractListService<Any, Toolkit>{

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AnyToolkitRepository repository;

	@Autowired
	protected AnyItemRepository itemRepository;
	
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
		
		StringBuilder payloadBuilder = new StringBuilder();
		
		request.unbind(entity, model, "title", "code", "description", "assemblyNotes", "link", "published");
		
		Collection<Item> listItems = itemRepository.findItemsByToolkitId(entity.getId());
		
		for (Item i : listItems) {
			payloadBuilder.append(i.getName());
			payloadBuilder.append(", ");
		}
		
		String payload = payloadBuilder.toString();
		
		model.setAttribute("payload", payload);
	}

}
