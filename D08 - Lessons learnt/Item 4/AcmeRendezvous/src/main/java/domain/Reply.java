
package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Reply extends DomainEntity {

	private Date	moment;
	private String	text;


	@NotNull
	public Date getMoment() {
		return this.moment;
	}
	@NotBlank
	public String getText() {
		return this.text;
	}

	public void setMoment(final Date moment) {
		this.moment = moment;
	}

	public void setText(final String text) {
		this.text = text;
	}


	//Relationships

	private User	user;


	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public User getUser() {
		return this.user;
	}

	public void setUser(final User user) {
		this.user = user;
	}

}
