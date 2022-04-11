package acme.features.inventor.toolkit;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.item.Item;
import acme.entities.toolkit.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;

@Service
public class InventorToolkitShowService implements AbstractShowService<Inventor, Toolkit> {
	// Internal state ---------------------------------------------------------

		@Autowired
		protected InventorToolkitRepository repository;


		@Override
		public boolean authorise(final Request<Toolkit> request) {
			assert request != null;
			return true;
		}

		@Override
		public void unbind(final Request<Toolkit> request, final Toolkit entity, final Model model) {
			assert request != null;
			assert entity != null;
			assert model != null;
			List<Item> items;
			items=this.repository.findItemsByToolkitId(entity.getId()).stream().collect(Collectors.toList());
			model.setAttribute("items", items);
			request.unbind(entity, model, "title", "code", "description", "assemblyNotes","link");
		}

		@Override
		public Toolkit findOne(final Request<Toolkit> request) {
			assert request != null;

			Toolkit result;
			int id;
			
			id = request.getModel().getInteger("id");
			result = this.repository.findToolkitById(id);

			return result;
		}
}
