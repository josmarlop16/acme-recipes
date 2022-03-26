package acme.features.anonymous.toolkit;

import org.springframework.stereotype.Controller;

import acme.framework.controllers.AbstractController;
import javax.annotation.PostConstruct;


import acme.framework.roles.Anonymous;
import acme.entities.toolkit.*;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class AnonymousToolkitController extends AbstractController<Anonymous, Toolkit>{
	
	// Internal state ---------------------------------------------------------

	@Autowired
	protected AnonymousToolkitListAllService listAllService;

	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addCommand("list", this.listAllService);
	}
	
}
