
package services;

import java.util.Collection;
import java.util.HashSet;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.AnswerRepository;
import domain.Answer;
import domain.Question;
import domain.Rendezvous;

@Service
@Transactional
public class AnswerService {

	// Managed Repository ----------------------------------------------------

	@Autowired
	private AnswerRepository	answerRepository;

	// Supporting services ----------------------------------------------------
	@Autowired
	private RendezvousService	rendezvousService;
	@Autowired
	private ActorService		actorService;
	@Autowired
	private QuestionService		questionService;


	// Constructor ----------------------------------------------------

	public AnswerService() {
		super();
	}

	// CRUD methods ----------------------------------------------------
	public Answer create(final Integer questionId) {

		Assert.notNull(questionId);

		Answer answer;
		answer = new Answer();

		//le asignamos el rendezvous
		final Question question = this.questionService.findOne(questionId);
		answer.setQuestion(question);

		return answer;
	}

	public Collection<Answer> findAll() {
		Collection<Answer> result;

		result = this.answerRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public Answer save(final Answer answer) {

		Assert.notNull(answer);

		final Answer result = this.answerRepository.save(answer);

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
}
