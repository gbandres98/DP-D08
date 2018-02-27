
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
import services.QuestionService;
import services.RendezvousService;
import controllers.AbstractController;
import domain.Answer;
import domain.Question;
import domain.Rendezvous;

@Controller
@RequestMapping("/question/user")
public class QuestionUserController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private RendezvousService	rendezvousService;

	@Autowired
	private QuestionService		questionService;

	@Autowired
	private ActorService		actorService;


	// Constructors -----------------------------------------------------------

	public QuestionUserController() {
		super();
	}

	// Creation ---------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final int rendezvousId) {
		ModelAndView result;
		Question question;

		question = this.questionService.create(rendezvousId);
		result = this.createEditModelAndView(question);

		return result;
	}

	// Edition ---------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int questionId) {
		ModelAndView result;
		Question question;
		Rendezvous rendezvous;
		Collection<Answer> answers;

		question = this.questionService.findOne(questionId);
		Assert.notNull(question);
		rendezvous = this.rendezvousService.findOne(question.getRendezvous().getId());
		answers = question.getAnswers();
		result = this.createEditModelAndView(question);
		result.addObject("rendezvous", rendezvous);
		result.addObject("answers", answers);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView edit(@Valid final Question question, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(question);
		else
			try {
				this.questionService.save(question);
				result = new ModelAndView("redirect:/question/list.do?rendezvousId=" + question.getRendezvous().getId());
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(question, "question.commit.error");
			}

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView cancel(final Question question) {
		ModelAndView result;

		this.questionService.delete(question);
		result = new ModelAndView("redirect:/question/list.do?rendezvousId=" + question.getRendezvous().getId());

		return result;
	}

	// Ancillary Methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(final Question question) {
		ModelAndView result;

		result = this.createEditModelAndView(question, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Question question, final String messageCode) {
		ModelAndView result;

		result = new ModelAndView("question/edit");
		result.addObject("question", question);
		result.addObject("message", messageCode);

		return result;
	}

}
