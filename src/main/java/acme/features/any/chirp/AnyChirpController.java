package acme.features.any.chirp;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.chirp.Chirp;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Any;

@Controller
public class AnyChirpController extends AbstractController<Any, Chirp>{
	
	// Internal state ---------------------------------------------------------

	@Autowired
	protected AnyChirpListAllService listAllService;
	
	@Autowired
	protected AnyChirpCreateService createService;
	
	@Autowired
	protected AnyChirpShowService showService;

	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addCommand("list", this.listAllService);
		super.addCommand("create", this.createService);
		super.addCommand("show", this.showService);
	}
	
}