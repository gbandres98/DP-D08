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
import java.util.ListResourceBundle;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Actor;
import domain.Comment;
import domain.Rendezvous;
import domain.User;

import services.ActorService;
import services.CommentService;
import services.RendezvousService;

@Controller
@RequestMapping("/comment")
public class CommentController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private CommentService commentService;
	@Autowired
	private ActorService actorService;
	@Autowired
	private RendezvousService rendezvousService;

	// Constructors -----------------------------------------------------------

	public CommentController() {
		super();
	}

	// Creation ---------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(
			@RequestParam(required = true) final Integer rendezvousId,
			@RequestParam(required = false) final Integer commentId) {
		ModelAndView result;
		Comment comment;

		comment = commentService.create(rendezvousId, commentId);
		result = createEditModelAndView(comment);

		return result;
	}

	// Listing Root
	// ----------------------------------------------------------------

	@RequestMapping(value = "/list-Root", method = RequestMethod.GET)
	public ModelAndView listRoot(
			@RequestParam(required = true) final Integer rendezvousId) {
		ModelAndView result;
		Collection<Comment> comments;
		result = new ModelAndView("comment/list");
		Actor actor = this.actorService.findByPrincipal();
		comments = commentService.findByRendezvousIdRoot(rendezvousId);
		
		//Esto es para que no salga el create comment si no tiene RSVP
		if (actor instanceof User){
		User user =  (User) this.actorService.findByPrincipal();
		Collection<Rendezvous> rendevouses =this.rendezvousService.findRendevousWithRSVPbyUserId(user.getId());
		Collection<Rendezvous> rendezvousesCreados=user.getRendezvouses() ;
		rendevouses.addAll(rendezvousesCreados);
		result.addObject("rendezvousesWithRSVP",rendevouses);
		}
	
		result.addObject("comments", comments);
		result.addObject("requestURI", "/comment/list-Root.do");
		
		return result;
	}

	// Listing
	// Answers----------------------------------------------------------------

	@RequestMapping(value = "/list-Answer", method = RequestMethod.GET)
	public ModelAndView listAnswer(
			@RequestParam(required = true) final Integer commentId) {
		ModelAndView result;
		result = new ModelAndView("comment/list");
		Actor actor = this.actorService.findByPrincipal();
		
		//Esto es para que no salga el create comment si no tiene RSVP
		if (actor instanceof User){
		User user =  (User) this.actorService.findByPrincipal();
		Collection<Rendezvous> rendevouses =this.rendezvousService.findRendevousWithRSVPbyUserId(user.getId());
		Collection<Rendezvous> rendezvousesCreados=user.getRendezvouses() ;
		rendevouses.addAll(rendezvousesCreados);
		result.addObject("rendezvousesWithRSVP",rendevouses);
		}
		
		Collection<Comment> comments;
		Comment parentComment;
		comments = commentService.findByParentCommentId(commentId);
		parentComment = commentService.findOne(commentId);
		
		result.addObject("comments", comments);
		result.addObject("ParentComment", parentComment);
		result.addObject("requestURI", "/comment/list-Answer.do");
		return result;
	}

	// delete from listing
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView display3(
			@RequestParam(required = true) final Integer commentId) {
		ModelAndView result;
		Comment c = commentService.findOne(commentId);
		Rendezvous r = c.getRendezvous();

		try {

			commentService.delete(c);
			if (c.getparentComment() == null) {
				result = this.listRoot(r.getId());

			} else {
				result = this.listAnswer(c.getparentComment().getId());
			}

		} catch (Throwable oops) {
			result = listAnswer(c.getId());

		}

		return result;
	}

	// Edition ----------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int commentId) {
		ModelAndView result;
		Comment comment;

		comment = commentService.findOne(commentId);

		result = this.createEditModelAndView(comment);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Comment comment,
			final BindingResult binding) {
		ModelAndView result;
		Rendezvous r = comment.getRendezvous();
		if (binding.hasErrors()){
//			result = this.createEditModelAndView(comment,"comment.binding.error"); esto si quieres que te salga blinding error en pantalla al equivocarte
			result = this.createEditModelAndView(comment);
		}else
			try {
				this.commentService.save(comment);
				result = new ModelAndView("redirect:/rendezvous/display.do?rendezvousId="+r.getId());
			} catch (final Throwable oops) {
				String errorMessage = "comment.commit.error";

				if (oops.getMessage().contains("message.error")) {
					errorMessage = oops.getMessage();
				}
				result = this.createEditModelAndView(comment, errorMessage);
			}

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final Comment comment,
			final BindingResult binding) {
		ModelAndView result;

		try {
			this.commentService.delete(comment);
			result = new ModelAndView("redirect:/rendezvous/display.do?IdRendevous=" + comment.getRendezvous().getId());
		} catch (final Throwable oops) {
			String errorMessage = "comment.commit.error";

			if (oops.getMessage().contains("message.error")) {
				errorMessage = oops.getMessage();
			}
			result = this.createEditModelAndView(comment, errorMessage);
		}
		return result;
	}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(final Comment comment) {
		ModelAndView result;

		result = this.createEditModelAndView(comment, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Comment comment,
			final String message) {
		ModelAndView result;

		result = new ModelAndView("comment/edit");
		result.addObject("comment", comment);
		result.addObject("message", message);

		return result;
	}
}
