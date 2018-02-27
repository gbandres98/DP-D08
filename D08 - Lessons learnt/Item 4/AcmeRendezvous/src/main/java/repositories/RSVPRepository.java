
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.RSVP;

@Repository
public interface RSVPRepository extends JpaRepository<RSVP, Integer> {

	@Query("select r from RSVP r where r.rendezvous.id=?1 and r.joined=true")
	Collection<RSVP> findJoinedByRendezvousId(int rendezvousId);

	@Query("select r from RSVP r where r.rendezvous.id=?1 and r.joined=false")
	Collection<RSVP> findNotJoinedByRendezvousId(int rendezvousId);

	@Query("select r from RSVP r where r.rendezvous.id=?1")
	Collection<RSVP> findByRendezvousId(int rendezvousId);

	@Query("select r from RSVP r where r.user.id=?1")
	Collection<RSVP> findByUser(int userId);

	@Query("select 1.0*count(r)/(select count(re) from Rendezvous re) from RSVP r where r.joined=true ")
	Double averageRSVPperRendezvous();

	@Query("select 1.0*count(r)/ (select count(u) from User u) from RSVP r where r.joined=true ")
	Double averageRSVPperUser();

	@Query("select r from RSVP r where r.rendezvous.id=?1 and r.user.id=?2")
	RSVP existByRendezvousIdUserId(int rendezvousId, int userId);

}
