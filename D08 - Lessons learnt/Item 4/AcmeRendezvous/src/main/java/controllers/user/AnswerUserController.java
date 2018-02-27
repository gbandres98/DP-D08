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

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.AnswerService;
import services.QuestionService;
import services.RSVPService;
import controllers.AbstractController;
import domain.Answer;
import domain.Question;
import domain.RSVP;

@Controller
@RequestMapping("/answer/user")
public class AnswerUserController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private AnswerService	answerService;

	@Autowired
	private QuestionService	questionService;

	@Autowired
	private RSVPService		rsvpService;


	// Constructors -----------------------------------------------------------

	public AnswerUserController() {
		super();
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final int rsvpId) {
		ModelAndView result;
		Answer answer;
		Question question;
		RSVP rsvp;

		rsvp = this.rsvpService.findOne(rsvpId);
		question = this.questionService.findNext(rsvpId);
		if (question == null) {
			result = new ModelAndView("redirect:/rendezvous/display.do?rendezvousId=" + rsvp.getRendezvous().getId());
			result.addObject("rsvpDONE", 1);
		} else {
			answer = this.answerService.create(question.getId());
			result = this.createEditModelAndView(answer);
			result.addObject("rsvpId", rsvpId);
		}

		return result;
	}
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView edit(@Valid final Answer answer, final BindingResult binding, @RequestParam final int rsvpId) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(answer);
		else
			try {
				this.answerService.save(answer, rsvpId);
				result = new ModelAndView("redirect:/answer/user/create.do?rsvpId=" + rsvpId);
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(answer, "answer.commit.error");
			}

		return result;
	}

	// Ancillary Methods ------------------------------------------------------
	protected ModelAndView createEditModelAndView(final Answer answer) {
		ModelAndView result;

		result = this.createEditModelAndView(answer, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Answer answer, final String messageCode) {
		ModelAndView result;

		result = new ModelAndView("answer/edit");
		result.addObject("answer", answer);
		result.addObject("message", messageCode);

		return result;
	}

}
