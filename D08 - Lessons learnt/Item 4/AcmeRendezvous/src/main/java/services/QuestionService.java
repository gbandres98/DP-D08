
package services;

import java.util.Collection;
import java.util.HashSet;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.QuestionRepository;
import domain.Actor;
import domain.Answer;
import domain.Question;
import domain.RSVP;
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
	@Autowired
	private RSVPService			rsvpService;


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
		final Collection<Answer> answers = new HashSet<Answer>();
		question.setAnswers(answers);

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
		Rendezvous rendezvous;

		actor = this.actorService.findByPrincipal();
		rendezvous = question.getRendezvous();
		Assert.isTrue(actor instanceof User);
		Assert.isTrue(actor.getId() == question.getRendezvous().getUser().getId());
		Assert.isTrue(rendezvous.isFinalVersion() == false);
		result = this.questionRepository.save(question);

		return result;
	}

	public void delete(final Question question) {

		//		final Actor actor;
		Assert.notNull(question);
		//		actor = this.actorService.findByPrincipal();
		//el admin tambien puede borrar
		//		Assert.isTrue(actor.getId() == question.getRendezvous().getUser().getId());
		//need admin to delete :borra tambien las respuestas(profesor fernando dio visto bueno)

		final Collection<Answer> answers = this.answerService.findByQuestionId(question.getId());
		for (final Answer a : answers)
			this.answerService.delete(a);
		Assert.isTrue(question.getRendezvous().isFinalVersion() == false);
		this.questionRepository.delete(question);

	}

	public Question findOne(final int questionId) {
		Question result;

		result = this.questionRepository.findOne(questionId);

		return result;
	}

	public Collection<Question> findByRendezvous(final Rendezvous rendezvous) {
		final Collection<Question> result;

		result = rendezvous.getQuestions();

		return result;
	}

	public Collection<Question> findByUser(final int userId) {
		Collection<Question> result;

		result = this.questionRepository.findByUser(userId);

		return result;
	}

	public Double averageQuestionsperRendezvous() {
		final Double result = this.questionRepository.averageQuestionsperRendezvous();
		return result;
	}

	public Double standardDeviationQuestionsperRendezvous() {
		final Double result = this.questionRepository.standardDeviationQuestionsperRendezvous();
		return result;
	}

	public Question findNext(final int rsvpId) {
		RSVP rsvp;
		Collection<Question> answered, notAnswered;
		Question result;

		rsvp = this.rsvpService.findOne(rsvpId);
		notAnswered = rsvp.getRendezvous().getQuestions();
		answered = this.questionRepository.findAnsweredByRSVP(rsvpId);
		notAnswered.removeAll(answered);

		//TODO testear esto
		if (notAnswered.isEmpty())
			result = null;
		else
			result = notAnswered.iterator().next();
		return result;
	}

}
