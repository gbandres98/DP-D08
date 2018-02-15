
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import domain.Rendezvous;
import domain.User;

@Repository
public interface RendezvousRepository extends JpaRepository<Rendezvous, Integer> {

	@Query("select r.users from Rendezvous r where r.id=?1")
	Collection<User> findRSVPByRendezvousId(int id);
}
