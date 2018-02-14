
package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Announcement extends DomainEntity {

	private Date	moment;
	private String	title;
	private String	description;


	@NotNull
	public Date getMoment() {
		return this.moment;
	}
	@NotBlank
	public String getTitle() {
		return this.title;
	}
	@NotBlank
	public String getDescription() {
		return this.description;
	}

	public void setMoment(final Date moment) {
		this.moment = moment;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	//Relationships

}
