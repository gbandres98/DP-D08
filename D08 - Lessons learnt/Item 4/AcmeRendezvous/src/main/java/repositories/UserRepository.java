
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	// Dashboard -----------------------------------------------------------

	@Query("select count(u)*100/(select count(ru) from User ru where ru.rendezvouses.size=0) from User u where u.rendezvouses.size>0")
	Double ratioUserRendezvousvsUserNoRendezvous();

	@Query("select u from User u where u.userAccount.id = ?1")
	User findByUserAccount(int id);

}
