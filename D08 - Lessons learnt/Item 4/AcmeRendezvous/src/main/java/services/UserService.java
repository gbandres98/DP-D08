
package services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.UserRepository;
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

		return result;
	}

}
