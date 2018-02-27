
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Answer;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Integer> {

	@Query("select r.answers from RSVP r where r.rendezvous.id=?1 and r.user.id=?2")
	Collection<Answer> findByRendezvousUser(int rendezvousId, int userId);

	@Query("select q.answers from Question q where q.rendezvous.id=?1")
	Collection<Answer> findByRendezvousID(int rendezvousID);

	@Query("select 1.0*count(a)/(select count(re) from Rendezvous re) from Answer a")
	Double averageAnswersperRendezvous();
}
