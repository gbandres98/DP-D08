
package services;

import java.util.Collection;
import java.util.HashSet;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.UserRepository;
import security.LoginService;
import security.UserAccount;
import domain.Rendezvous;
import domain.User;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository	userRepository;


	//Constructors
	public UserService() {
		super();
	}

	public User create() {
		User result;

		result = new User();
		result.setRendezvouses(new HashSet<Rendezvous>());

		return result;
	}

	public Collection<User> findAll() {
		Collection<User> result;

		result = this.userRepository.findAll();

		return result;
	}

	public User findOne(final int userId) {
		User result;

		result = this.userRepository.findOne(userId);
		Assert.notNull(result);

		return result;
	}

	public User findByPrincipal() {
		User result;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		result = this.findByUserAccount(userAccount);
		return result;
	}

	public User findByUserAccount(final UserAccount userAccount) {
		User result;
		result = this.userRepository.findByUserAccount(userAccount.getId());

		return result;
	}

	public Double ratioUserRendezvousvsUserNoRendezvous() {
		final Double result = this.userRepository.ratioUserRendezvousvsUserNoRendezvous();
		return result;
	}

}
