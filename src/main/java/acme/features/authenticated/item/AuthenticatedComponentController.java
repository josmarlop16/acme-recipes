/*
 * AnonymousShoutController.java
 *
 * Copyright (C) 2012-2022 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.authenticated.item;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.item.Item;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Authenticated;

@Controller
public class AuthenticatedComponentController extends AbstractController<Authenticated, Item> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AuthenticatedComponentListService		listService;
	
	@Autowired
	protected AuthenticatedComponentShowService		showService;
	
	@Autowired
	protected AuthenticatedComponentToolListService		toollistService;
	
	@Autowired
	protected AuthenticatedComponentToolShowService		toolshowService;

	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addCommand("list-component", "list", this.listService);
		super.addCommand("list-tool", "list", this.toollistService);
		super.addCommand("show", this.showService);
	}

}
