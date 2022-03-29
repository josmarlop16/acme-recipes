package acme.features.authenticated.chirp;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.chirp.Chirp;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Authenticated;

@Controller
public class AuthenticatedChirpController extends AbstractController<Authenticated, Chirp>{
	
	// Internal state ---------------------------------------------------------

	@Autowired
	protected AuthenticatedChirpListAllService listAllService;
	
	@Autowired
	protected AuthenticatedChirpShowService showService;

	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addCommand("list", this.listAllService);
		super.addCommand("show", this.showService);
	}
	
}