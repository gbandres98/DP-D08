
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.CommentRepository;
import domain.Actor;
import domain.Administrator;
import domain.Comment;
import domain.Rendezvous;
import domain.User;

@Service
@Transactional
public class CommentService {

	// Managed Repository ----------------------------------------------------

	@Autowired
	private CommentRepository	commentRepository;

	// Supporting services ----------------------------------------------------
	@Autowired
	private RendezvousService	rendezvousService;
	@Autowired
	private ActorService		actorService;


	// Constructor ----------------------------------------------------

	public CommentService() {
		super();
	}

	// CRUD methods ----------------------------------------------------
	public Comment create(final Integer rendezvousId, final Integer commentId) {

		//el id del rendezvous no puede ser nulo, en cambio el del comentarioPadre si(en ese caso no es una respuesta
		Assert.notNull(rendezvousId);

		Comment comment;
		comment = new Comment();

		//le asignamos el rendezvous
		final Rendezvous rendezvous = this.rendezvousService.findOne(rendezvousId);
		comment.setRendezvous(rendezvous);

		//añadimos el comentario padre en caso de haberlo 
		if (commentId != null) {
			final Comment parentComment = this.commentRepository.findOne(commentId);
			comment.setparentComment(parentComment);
		}
		//Sacamos el momento del sistema
		Date moment;
		moment = new Date(System.currentTimeMillis() - 1);
		comment.setMoment(moment);

		//añadimos el actor
		Actor actor;
		actor = this.actorService.findByPrincipal();
		Assert.isTrue(actor != null);
		Assert.isTrue(actor instanceof User);

		final User user = (User) actor;
		comment.setUser(user);

		return comment;
	}

	public Collection<Comment> findAll() {
		Collection<Comment> result;

		result = this.commentRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public Comment save(final Comment comment) {

		Assert.notNull(comment);
		//Sacamos el momento del sistema
		Date moment;
		moment = new Date(System.currentTimeMillis() - 1);
		comment.setMoment(moment);

		//checkear que el usuario tiene RSPV en el rendevous que comenta
		final User user = (User) this.actorService.findByPrincipal();
		final Collection<Rendezvous> rendevouses = this.rendezvousService.findRendevousWithRSVPbyUserId(user.getId());
		final Collection<Rendezvous> rendezvousesCreados = user.getRendezvouses();
		rendevouses.addAll(rendezvousesCreados);
		Assert.isTrue(rendevouses.contains(comment.getRendezvous()));

		final Comment commentsave = this.commentRepository.save(comment);
		return commentsave;
	}
	public void delete(final Comment comment) {

		final Actor actor;
		Assert.notNull(comment);
		actor = this.actorService.findByPrincipal();
		Assert.isTrue(actor != null);

		//requeriment 6.1 need admin to delete :borra tambien las respuestas(profesor fernando dio visto bueno)
		Assert.isTrue(actor instanceof Administrator);
		final Collection<Comment> childrenComment = this.commentRepository.findByParentCommentId(comment.getId());
		for (final Comment c : childrenComment)
			this.delete(c);
		this.commentRepository.delete(comment);

	}

	// Other business methods

	public Comment findOne(final int commentId) {
		Comment result;

		result = this.commentRepository.findOne(commentId);

		return result;
	}

	public Collection<Comment> findByRendezvous(final Rendezvous rendezvous) {
		final Collection<Comment> result;

		// Tested Query 
		result = this.commentRepository.findByRendezvousId(rendezvous.getId());

		return result;
	}
	public Collection<Comment> findByParentComment(final Comment comment) {
		final Collection<Comment> result;

		// Tested Query 
		result = this.commentRepository.findByParentCommentId(comment.getId());

		return result;
	}
	public Collection<Comment> findByRendezvousId(final int rendezvousId) {
		final Collection<Comment> result;

		// Tested Query 
		result = this.commentRepository.findByRendezvousId(rendezvousId);

		return result;
	}
	public Collection<Comment> findByParentCommentId(final int commentId) {
		final Collection<Comment> result;

		// Tested Query 
		result = this.commentRepository.findByParentCommentId(commentId);

		return result;
	}
	public Collection<Comment> findByRendezvousIdRoot(final int rendezvousId) {
		final Collection<Comment> result;

		// Tested Query 
		result = this.commentRepository.findByRendezvousIdRoot(rendezvousId);

		return result;
	}

	public Double averageReplysperComment() {
		final Collection<Comment> aux = this.commentRepository.findAll();
		Collection<Comment> auxc = new ArrayList<Comment>();
		Double auxs = 0.0;
		for (final Comment c : aux) {
			auxc = this.findByParentCommentId(c.getId());
			auxs = auxs + auxc.size();
		}

		final Double result = 1.0 * auxs / aux.size();
		return result;
	}

	public Double standardDeviationReplysperComment() {
		final Double avg = this.averageReplysperComment();
		final Collection<Comment> aux = this.commentRepository.findAll();
		Collection<Comment> auxc = new ArrayList<Comment>();
		Double auxsum = 0.0;
		for (final Comment c : aux) {
			auxc = this.findByParentCommentId(c.getId());
			auxsum = auxsum + (auxc.size() * auxc.size());
		}

		final Double result = Math.sqrt((auxsum / aux.size()) - (avg * avg));

		return result;
	}
}
