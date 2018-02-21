
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.QuestionRepository;
import domain.Actor;
import domain.Answer;
import domain.Question;
import domain.Rendezvous;
import domain.User;

@Service
@Transactional
public class QuestionService {

	// Managed Repository ----------------------------------------------------

	@Autowired
	private QuestionRepository	questionRepository;

	// Supporting services ----------------------------------------------------
	@Autowired
	private RendezvousService	rendezvousService;
	@Autowired
	private ActorService		actorService;
	@Autowired
	private AnswerService		answerService;


	// Constructor -----------------------------------------------------------

	public QuestionService() {
		super();
	}

	// CRUD Methods ----------------------------------------------------------

	public Question create(final Integer rendezvousId) {

		//el id del rendezvous no puede ser nulo
		Assert.notNull(rendezvousId);

		Question question;
		question = new Question();

		//le asignamos el rendezvous
		final Rendezvous rendezvous = this.rendezvousService.findOne(rendezvousId);
		question.setRendezvous(rendezvous);

		return question;
	}

	public Collection<Question> findAll() {
		Collection<Question> result;

		result = this.questionRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public Question save(final Question question) {
		Assert.notNull(question);
		Actor actor;
		Question result;

		actor = this.actorService.findByPrincipal();
		Assert.isTrue(actor instanceof User);
		Assert.isTrue(actor.getId() == question.getRendezvous().getUser().getId());
		result = this.questionRepository.save(question);

		return result;
	}

	public void delete(final Question question) {

		final Actor actor;
		Assert.notNull(question);
		actor = this.actorService.findByPrincipal();
		Assert.isTrue(actor.getId() == question.getRendezvous().getUser().getId());
		//requeriment 6.1 need admin to delete :borra tambien las respuestas(profesor fernando dio visto bueno)

		final Collection<Answer> answers = this.answerService.findByQuestionId(question.getId());
		for (final Answer a : answers)
			answers.remove(a);
		this.questionRepository.delete(question);

	}

	public Question findOne(final int questionId) {
		Question result;

		result = this.questionRepository.findOne(questionId);

		return result;
	}

	public Collection<Question> findByRendezvous(final Rendezvous rendezvous) {
		final Collection<Question> result;

		result = this.questionRepository.findByRendezvousId(rendezvous.getId());

		return result;
	}
}
