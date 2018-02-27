
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Rendezvous;

@Repository
public interface RendezvousRepository extends JpaRepository<Rendezvous, Integer> {

	@Query("select r from Rendezvous r join r.announcements a where a.id = ?1")
	Rendezvous findByAnnouncementId(int id);

	@Query("select r.rendezvous from RSVP r where r.user.id = ?1 and r.joined=1")
	Collection<Rendezvous> findRendevousWithRSVPbyUserId(int id);

	@Query("select r from Rendezvous r where r.user.id = ?1")
	Collection<Rendezvous> findByUser(int userId);

	@Query("select r from Rendezvous r where r.finalVersion = true")
	Collection<Rendezvous> findAllFinal();

	// Dashboard -----------------------------------------------------------

	@Query("select avg(u.rendezvouses.size) from User u")
	Double averageRendezvousesperUser();
	//Standar Deviation
	@Query("select sqrt(sum(u.rendezvouses.size*u.rendezvouses.size) / count(u.rendezvouses.size) - (avg(u.rendezvouses.size) * avg(u.rendezvouses.size))) from User u")
	Double standardDeviationRendezvousesperUser();

	@Query("select r.rendezvouses from Rendezvous r where r.id = ?1")
	Collection<Rendezvous> findSimilar(int rendezvousId);

	@Query("select r from Rendezvous r where r.announcements.size>= (select avg(rt.announcements.size)*1.75 from Rendezvous rt)")
	Collection<Rendezvous> findRendezvousWithMoreAnnouncementsThanAverage();

	@Query("select r from Rendezvous r where r.rendezvouses.size>= (select avg(rt.rendezvouses.size)*1.1 from Rendezvous rt)")
	Collection<Rendezvous> findRendezvousWithMoreRendezvousesThanAverage();

}
