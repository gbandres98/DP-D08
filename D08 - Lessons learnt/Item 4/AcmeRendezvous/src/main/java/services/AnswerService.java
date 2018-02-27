
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.AnswerRepository;
import domain.Actor;
import domain.Answer;
import domain.Question;
import domain.RSVP;
import domain.Rendezvous;

@Service
@Transactional
public class AnswerService {

	// Managed Repository ----------------------------------------------------

	@Autowired
	private AnswerRepository	answerRepository;

	// Supporting services ----------------------------------------------------
	@Autowired
	private ActorService		actorService;
	@Autowired
	private QuestionService		questionService;
	@Autowired
	private RSVPService			rsvpService;
	@Autowired
	private RendezvousService	rendezvousService;


	// Constructor ----------------------------------------------------

	public AnswerService() {
		super();
	}

	// CRUD methods ----------------------------------------------------
	public Answer create(final Integer questionId) {
		Question question;

		Assert.notNull(questionId);

		Answer answer;
		answer = new Answer();

		//le asignamos el rendezvous
		question = this.questionService.findOne(questionId);
		answer.setQuestion(question);

		return answer;
	}

	public Collection<Answer> findAll() {
		Collection<Answer> result;

		result = this.answerRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public Answer save(final Answer answer, final int rsvpId) {
		RSVP rsvp;
		Answer result;
		Actor actor;

		actor = this.actorService.findByPrincipal();
		rsvp = this.rsvpService.findOne(rsvpId);
		Assert.notNull(answer);
		Assert.isTrue(rsvp.getUser().getId() == actor.getId());
		Assert.isTrue(!rsvp.isJoined());
		Assert.isTrue(rsvp.getRendezvous().getQuestions().contains(answer.getQuestion()));

		result = this.answerRepository.save(answer);

		rsvp.addAnswer(result);
		this.rsvpService.save(rsvp);

		return result;
	}
	public void delete(final Answer answer) {

		Assert.notNull(answer);

		this.answerRepository.delete(answer);

	}

	// Other business methods

	public Answer findOne(final int answerId) {
		Answer result;

		result = this.answerRepository.findOne(answerId);

		return result;
	}

	public Collection<Answer> findByRendezvous(final Rendezvous rendezvous) {
		Collection<Answer> result;
		Collection<Question> questions;

		questions = this.questionService.findByRendezvous(rendezvous);
		result = new HashSet<Answer>();
		for (final Question q : questions)
			result = q.getAnswers();

		return result;
	}

	public Collection<Answer> findByQuestionId(final int questionId) {
		Collection<Answer> result;
		Question question;

		question = this.questionService.findOne(questionId);
		result = question.getAnswers();

		return result;
	}

	public Collection<Answer> findByRendezvousUser(final int rendezvousId, final int userId) {
		Collection<Answer> result;

		result = this.answerRepository.findByRendezvousUser(rendezvousId, userId);

		return result;
	}

	public Double averageAnswersperRendezvous() {
		final Double result = this.answerRepository.averageAnswersperRendezvous();
		return result;
	}

	public Double standardDeviationAnswersperRendezvous() {
		final Double avg = this.averageAnswersperRendezvous();
		final Collection<Rendezvous> aux = this.rendezvousService.findAll();
		Collection<Answer> auxa = new ArrayList<Answer>();
		Double auxsum = 0.0;
		for (final Rendezvous r : aux) {
			auxa = this.answerRepository.findByRendezvousID(r.getId());
			auxsum = auxsum + (auxa.size() * auxa.size());
		}

		final Double result = Math.sqrt((auxsum / aux.size()) - (avg * avg));

		return result;
	}

}
