
package services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.AdministratorRepository;
import domain.Administrator;

@Service
@Transactional
public class AdministratorService {

	@Autowired
	private AdministratorRepository	administratorRepository;


	//Constructors
	public AdministratorService() {
		super();
	}

	public Administrator create() {
		Administrator result;

		result = new Administrator();

		return result;
	}

}
