package acme.features.patron.patronageReport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronages.PatronageReport;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;
import acme.roles.Patron;

@Service
public class PatronPatronageReportShowMineService implements AbstractShowService<Patron, PatronageReport>{

	// Internal state ---------------------------------------------------------

		@Autowired
		protected PatronPatronageReportRepository repository;


		@Override
		public boolean authorise(final Request<PatronageReport> request) {
			assert request != null;
			return true;
		}

		@Override
		public void unbind(final Request<PatronageReport> request, final PatronageReport entity, final Model model) {
			assert request != null;
			assert entity != null;
			assert model != null;
			
			request.unbind(entity, model, "seqNumber", "creation", "memorandum", "optionalLink");
		}
		
		@Override
		public PatronageReport findOne(final Request<PatronageReport> request) {
			assert request != null;

			PatronageReport result;
			
			Principal principal;
			principal = request.getPrincipal();	
			
			final int patronId = principal.getActiveRoleId();
			final int patronageReportId = request.getModel().getInteger("id");
			
			result = this.repository.findPatronageReportById(patronageReportId, patronId);

			return result;
		}
		
}