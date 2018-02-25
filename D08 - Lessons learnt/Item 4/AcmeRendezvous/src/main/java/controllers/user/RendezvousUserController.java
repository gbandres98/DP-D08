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

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.RendezvousService;
import controllers.AbstractController;
import domain.Rendezvous;
import domain.User;
import forms.SimilarForm;

@Controller
@RequestMapping("/rendezvous/user")
public class RendezvousUserController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private RendezvousService	rendezvousService;

	@Autowired
	private ActorService		actorService;


	// Constructors -----------------------------------------------------------

	public RendezvousUserController() {
		super();
	}

	// Listing ----------------------------------------------------------------		

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Rendezvous> rendezvouses;
		User user;

		user = (User) this.actorService.findByPrincipal();
		rendezvouses = this.rendezvousService.findByUser(user.getId());

		result = new ModelAndView("rendezvous/list");
		result.addObject("rendezvouses", rendezvouses);
		result.addObject("requestURI", "rendezvous/user/list.do");
		result.addObject("userId", user.getId());
		return result;
	}

	@RequestMapping(value = "/listrsvp", method = RequestMethod.GET)
	public ModelAndView listrsvp() {
		ModelAndView result;
		Collection<Rendezvous> rendezvouses;
		User user;

		user = (User) this.actorService.findByPrincipal();
		rendezvouses = this.rendezvousService.findRendevousWithRSVPbyUserId(user.getId());

		result = new ModelAndView("rendezvous/list");
		result.addObject("rendezvouses", rendezvouses);
		result.addObject("requestURI", "rendezvous/user/listrsvp.do");
		return result;
	}
	// Creation ---------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Rendezvous rendezvous;

		rendezvous = this.rendezvousService.create();
		result = this.createEditModelAndView(rendezvous);

		return result;
	}

	@RequestMapping(value = "/similar", method = RequestMethod.GET)
	public ModelAndView similar(@RequestParam final int rendezvousId) {
		ModelAndView result;
		SimilarForm similarForm;
		Collection<Rendezvous> rendezvouses;
		Rendezvous rendezvous;

		similarForm = new SimilarForm(rendezvousId);
		rendezvouses = this.rendezvousService.findAll();
		rendezvous = this.rendezvousService.findOne(rendezvousId);
		rendezvouses.removeAll(rendezvous.getRendezvouses());

		result = new ModelAndView("rendezvous/similar");
		result.addObject("similarForm", similarForm);
		result.addObject("rendezvouses", rendezvouses);
		return result;
	}

	@RequestMapping(value = "/similar", method = RequestMethod.POST, params = "save")
	public ModelAndView similar(@Valid final SimilarForm similarForm, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = new ModelAndView("rendezvous/similar");
			result.addObject("similarForm", similarForm);
		} else
			try {
				this.rendezvousService.save(similarForm);
				result = new ModelAndView("redirect:/rendezvous/display.do?rendezvousId=" + similarForm.getRendezvous());
			} catch (final Throwable oops) {
				result = new ModelAndView("rendezvous/similar");
				result.addObject("similarForm", similarForm);
				result.addObject("message", "rendezvous.commit.error");
			}

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int rendezvousId) {
		ModelAndView result;
		Rendezvous rendezvous;

		rendezvous = this.rendezvousService.findOne(rendezvousId);
		Assert.notNull(rendezvous);
		result = this.createEditModelAndView(rendezvous);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView edit(@Valid final Rendezvous rendezvous, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(rendezvous);
		else
			try {
				this.rendezvousService.save(rendezvous);
				result = new ModelAndView("redirect:/rendezvous/user/list.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(rendezvous, "rendezvous.commit.error");
			}

		return result;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView cancel(@RequestParam final int rendezvousId) {
		ModelAndView result;

		this.rendezvousService.delete(rendezvousId);
		result = new ModelAndView("redirect:/rendezvous/display.do?rendezvousId=" + rendezvousId);

		return result;
	}

	@RequestMapping(value = "/setFinal", method = RequestMethod.GET)
	public ModelAndView setFinal(@RequestParam final int rendezvousId) {
		ModelAndView result;
		Rendezvous rendezvous;

		rendezvous = this.rendezvousService.setFinal(rendezvousId);

		result = new ModelAndView("redirect:/rendezvous/display.do?rendezvousId=" + rendezvous.getId());
		result.addObject("finalVersion", 1);

		return result;
	}

	// Ancillary Methods ------------------------------------------------------
	protected ModelAndView createEditModelAndView(final Rendezvous rendezvous) {
		ModelAndView result;

		result = this.createEditModelAndView(rendezvous, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Rendezvous rendezvous, final String messageCode) {
		ModelAndView result;

		result = new ModelAndView("rendezvous/edit");
		result.addObject("rendezvous", rendezvous);
		result.addObject("message", messageCode);

		return result;
	}

}
