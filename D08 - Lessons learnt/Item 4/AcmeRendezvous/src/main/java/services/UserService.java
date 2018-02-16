
package services;

import java.util.HashSet;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.UserRepository;
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
		result.setRSVP(new HashSet<Rendezvous>());

		return result;
	}

}
