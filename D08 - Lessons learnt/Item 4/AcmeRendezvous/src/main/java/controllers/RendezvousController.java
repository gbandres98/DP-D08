/*
 * WelcomeController.java
 * 
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.RendezvousService;
import domain.Rendezvous;
import domain.User;

@Controller
@RequestMapping("/rendezvous")
public class RendezvousController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private RendezvousService	rendezvousService;

	@Autowired
	private ActorService		actorService;


	// Constructors -----------------------------------------------------------

	public RendezvousController() {
		super();
	}

	// Listing ----------------------------------------------------------------		

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Rendezvous> rendezvouses;

		rendezvouses = this.rendezvousService.findAll();
		result = new ModelAndView("rendezvous/list");
		result.addObject("rendezvouses", rendezvouses);
		result.addObject("requestURI", "rendezvous/list.do");
		return result;
	}

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int rendezvousId) {
		ModelAndView result;
		Rendezvous rendezvous;
		User user;

		user = (User) this.actorService.findByPrincipal();
		rendezvous = this.rendezvousService.findOne(rendezvousId);

		result = new ModelAndView("rendezvous/display");
		result.addObject("rendezvous", rendezvous);
		result.addObject("requestURI", "rendezvous/display.do");
		result.addObject("userId", user.getId());
		return result;
	}

}
