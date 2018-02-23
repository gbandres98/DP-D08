
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
import domain.Question;
import domain.User;

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

	// Listing ----------------------------------------------------------------	

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Question> questions;
		User user;

		user = (User) this.actorService.findByPrincipal();
		questions = this.questionService.findByUser(user.getId());

		result = new ModelAndView("question/list");
		result.addObject("questions", questions);
		result.addObject("requestURI", "question/user/list.do");
		result.addObject("userId", user.getId());
		return result;
	}

	// Edition ---------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int questionId) {
		ModelAndView result;
		Question question;

		question = this.questionService.findOne(questionId);
		Assert.notNull(question);
		result = this.createEditModelAndView(question);

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
				result = new ModelAndView("redirect:/question/user/list.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(question, "question.commit.error");
			}

		return result;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView cancel(@RequestParam final int questionId) {
		ModelAndView result;

		this.rendezvousService.delete(questionId);
		result = new ModelAndView("redirect:/question/list.do?");

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
