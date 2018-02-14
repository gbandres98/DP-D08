
package services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.AnnouncementRepository;

@Service
@Transactional
public class AnnouncementService {

	// Managed Repository ----------------------------------------------------

	@Autowired
	private AnnouncementRepository	announcementRepository;


	// Supporting services ----------------------------------------------------

	//Constructor ----------------------------------------------------

	public AnnouncementService() {
		super();
	}

	//CRUD methods ----------------------------------------------------

}
