
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Announcement;

@Repository
public interface AnnouncementRepository extends JpaRepository<Announcement, Integer> {

	@Query("select a from Announcement a where a.rendezvous.id=?1")
	Collection<Announcement> findByRendezvousId(int id);

	//select a from RSVP r join r.rendezvous.announcements a
	@Query("select a from RSVP r join r.rendezvous.announcements a where r.user.id=?1")
	Collection<Announcement> findByUserRSVPId(int id);
}
