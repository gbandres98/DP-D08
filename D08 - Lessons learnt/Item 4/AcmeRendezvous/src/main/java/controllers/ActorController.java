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

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.RendezvousService;
import services.UserService;
import domain.Actor;
import domain.Rendezvous;
import domain.User;
import forms.ActorForm;

@Controller
@RequestMapping("/actor")
public class ActorController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private ActorService		actorService;

	@Autowired
	private UserService			userService;

	@Autowired
	private RendezvousService	rendezvousService;


	// Constructors -----------------------------------------------------------

	public ActorController() {
		super();
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() throws Exception {
		final ModelAndView result;
		final Collection<User> users;

		users = this.userService.findAll();

		result = new ModelAndView("actor/list");
		result.addObject("users", users);
		return result;
	}

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int userId) throws Exception {
		final ModelAndView result;
		User user;
		final Collection<Rendezvous> rendezvouses;

		user = this.userService.findOne(userId);
		rendezvouses = this.rendezvousService.findByUser(userId);

		result = new ModelAndView("actor/display");
		result.addObject("user", user);
		result.addObject("rendezvouses", rendezvouses);
		result.addObject("requestURI", "actor/display.do?userId=" + userId);
		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final String actorType) throws Exception {
		ModelAndView result;
		ActorForm actorForm;

		actorForm = new ActorForm();
		actorForm.setAuthority(actorType);

		result = this.createRegisterModelAndView(actorForm);
		return result;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST, params = "save")
	public ModelAndView register(@Valid final ActorForm actorForm, final BindingResult binding) {
		ModelAndView result;
		Actor actor;

		if (binding.hasErrors())
			result = this.createRegisterModelAndView(actorForm);
		else
			try {
				actor = this.actorService.create(actorForm);
				if (binding.hasErrors())
					result = this.createRegisterModelAndView(actorForm);
				else {
					actor = this.actorService.register(actor);
					result = new ModelAndView("redirect:/welcome/index.do");
				}
			} catch (final Throwable oops) {
				oops.printStackTrace();
				result = this.createRegisterModelAndView(actorForm, "actor.commit.error");
			}
		return result;
	}
	//	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	//	public ModelAndView edit() throws Exception {
	//		ModelAndView result;
	//		Actor actor;
	//
	//		actor = this.actorService.findByPrincipal();
	//
	//		result = this.createEditModelAndView(actor);
	//		return result;
	//	}
	//
	//	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	//	public ModelAndView edit(@Valid final Actor actor, final BindingResult binding) {
	//		ModelAndView result;
	//		SystemConfiguration systemConfig;
	//
	//		if (binding.hasErrors())
	//			result = this.createEditModelAndView(actor);
	//		else
	//			try {
	//				if (actor.getPhone().matches("\\d{4,99}")) {
	//					systemConfig = this.systemConfigurationService.findSystemConfiguration();
	//					String newPhone = systemConfig.getCountryCode();
	//					newPhone += " " + actor.getPhone();
	//					actor.setPhone(newPhone);
	//				}
	//				this.actorService.saveEdit(actor);
	//				result = new ModelAndView("redirect:/welcome/index.do");
	//			} catch (final Throwable oops) {
	//				result = this.createEditModelAndView(actor, "actor.commit.error");
	//				oops.printStackTrace();
	//			}
	//
	//		return result;
	//	}

	// Ancillary methods

	protected ModelAndView createRegisterModelAndView(final ActorForm actorForm) {
		ModelAndView result;

		result = this.createRegisterModelAndView(actorForm, null);

		return result;
	}
	protected ModelAndView createRegisterModelAndView(final ActorForm actorForm, final String message) {
		ModelAndView result;

		final String requestURI = "actor/register.do";

		result = new ModelAndView("actor/register");
		result.addObject("actorForm", actorForm);
		result.addObject("message", message);
		result.addObject("requestURI", requestURI);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Actor actor) {
		ModelAndView result;

		result = this.createEditModelAndView(actor, null);

		return result;
	}
	protected ModelAndView createEditModelAndView(final Actor actor, final String message) {
		ModelAndView result;

		final String requestURI = "actor/edit.do";

		result = new ModelAndView("actor/edit");
		result.addObject("actor", actor);
		result.addObject("message", message);
		result.addObject("requestURI", requestURI);

		return result;
	}

}
