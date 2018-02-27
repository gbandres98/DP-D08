
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
	@Query("select a from RSVP r join r.rendezvous.announcements a where r.user.id=?1 and r.joined = true")
	Collection<Announcement> findByUserRSVPId(int id);

	@Query("select avg(r.announcements.size) from Rendezvous r")
	Double averageAnnoucementsperRendezvous();
	//Standar Deviation
	@Query("select sqrt(sum(r.announcements.size*r.announcements.size) / count(r.announcements.size) - (avg(r.announcements.size) * avg(r.announcements.size))) from Rendezvous r")
	Double standardDeviationAnnoucementsperRendezvous();

}
