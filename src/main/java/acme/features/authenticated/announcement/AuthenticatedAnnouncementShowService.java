package acme.features.authenticated.announcement;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.announcement.Announcement;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Authenticated;
import acme.framework.services.AbstractShowService;
@Service
public class AuthenticatedAnnouncementShowService implements AbstractShowService<Authenticated, Announcement> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AuthenticatedAnnouncementRepository repository;


	@Override
	public boolean authorise(final Request<Announcement> request) {
		assert request != null;

		final int id = request.getModel().getInteger("id");
		final Announcement a = this.repository.findById(id);

		final Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);
		final Date oneMonthAgo = calendar.getTime();

		return a.getCreation().after(oneMonthAgo);
	}

	@Override
	public Announcement findOne(final Request<Announcement> request) {
		assert request != null;

		Announcement result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findById(id);

		return result;
	}

	@Override
	public void unbind(final Request<Announcement> request, final Announcement entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "creation", "title", "body", "critical", "optionalLink");
	
	}

}