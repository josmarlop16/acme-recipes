package acme.features.any.toolkit;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.item.Item;
import acme.entities.toolkit.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractListService;


@Service
public class AnyToolkitListAllService implements AbstractListService<Any, Toolkit>{

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AnyToolkitRepository repository;


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
		
		List<Item> items;
		items=this.repository.findItemsByToolkitId(entity.getId()).stream().collect(Collectors.toList());
		
		final List<String> itemNames = new ArrayList<>();
		
		for (final Item item:items) {
			itemNames.add(item.getName());
		}
		model.setAttribute("items", itemNames);
		
		request.unbind(entity, model, "title", "code", "description", "assemblyNotes", "link", "published");
	}

}
