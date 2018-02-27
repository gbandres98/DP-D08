
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {

	@Query("select r.questions from Rendezvous r where r.id=?1")
	Collection<Question> findByRendezvousId(int rendezvousId);

	@Query("select q from Question q where q.rendezvous.user.id=?1")
	Collection<Question> findByUser(int userId);

	@Query("select avg(r.questions.size) from Rendezvous r")
	Double averageQuestionsperRendezvous();
	//Standar Deviation
	@Query("select sqrt(sum(r.questions.size*r.questions.size) / count(r.questions.size) - (avg(r.questions.size) * avg(r.questions.size))) from Rendezvous r")
	Double standardDeviationQuestionsperRendezvous();

	@Query("select a.question from RSVP r join r.answers a where r.id=?1")
	Collection<Question> findAnsweredByRSVP(int rsvpId);

}
