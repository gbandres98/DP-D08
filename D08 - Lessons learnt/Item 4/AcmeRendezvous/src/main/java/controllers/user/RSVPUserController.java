/*
 * WelcomeController.java
 * 
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.RSVPService;
import controllers.AbstractController;
import domain.RSVP;

@Controller
@RequestMapping("/rsvp/user")
public class RSVPUserController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private RSVPService	rsvpService;


	// Constructors -----------------------------------------------------------

	public RSVPUserController() {
		super();
	}

	// Listing ----------------------------------------------------------------		

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final int rendezvousId) {
		ModelAndView result;
		RSVP rsvp;

		rsvp = this.rsvpService.create(rendezvousId);
		result = new ModelAndView("redirect:/answer/user/create.do?rsvpId=" + rsvp.getId());

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int rsvpId) {
		ModelAndView result;
		RSVP rsvp;
		Assert.isTrue(rsvpId != 0);
		rsvp = this.rsvpService.findOne(rsvpId);
		Assert.isTrue(rsvp != null);

		result = new ModelAndView("redirect:/answer/user/create.do?rsvpId=" + rsvpId);

		return result;
	}

	@RequestMapping(value = "/cancel", method = RequestMethod.GET)
	public ModelAndView cancel(@RequestParam final int rsvpId) {
		ModelAndView result;
		RSVP rsvp;

		rsvp = this.rsvpService.findOne(rsvpId);
		this.rsvpService.cancel(rsvp);
		result = new ModelAndView("redirect:/rendezvous/display.do?rendezvousId=" + rsvp.getRendezvous().getId());

		return result;
	}
}
