package acme.features.chef.toolkit;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.toolkit.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;
import acme.roles.Chef;

@Service
public class ChefToolkitListService implements AbstractListService<Chef, Toolkit> {
	// Internal state ---------------------------------------------------------

		@Autowired
		protected ChefToolkitRepository repository;


		@Override
		public boolean authorise(final Request<Toolkit> request) {
			assert request != null;

			return true;
		}

		@Override
		public Collection<Toolkit> findMany(final Request<Toolkit> request) {
			assert request != null;

			Collection<Toolkit> result;
			Principal principal;
			
			principal = request.getPrincipal();
			result = this.repository.findToolkitByChefId(principal.getActiveRoleId());

			return result;
		}
		
		@Override
		public void unbind(final Request<Toolkit> request, final Toolkit entity, final Model model) {
			assert request != null;
			assert entity != null;
			assert model != null;
			
			
			request.unbind(entity, model, "title", "code", "description", "assemblyNotes", "link", "published");
		}
}
