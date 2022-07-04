package acme.features.any.peep;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.peep.Peep;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Any;

@Controller
public class AnyPeepController extends AbstractController<Any, Peep>{
	
	// Internal state ---------------------------------------------------------

	@Autowired
	protected AnyPeepListAllService listAllService;
	
	@Autowired
	protected AnyPeepCreateService createService;
	
	@Autowired
	protected AnyPeepShowService showService;

	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addCommand("list", this.listAllService);
		super.addCommand("create", this.createService);
		super.addCommand("show", this.showService);
	}
	
}