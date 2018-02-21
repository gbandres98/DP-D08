
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
}
