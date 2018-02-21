
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.RSVP;

@Component
@Transactional
public class RSVPToStringConverter implements Converter<RSVP, String> {

	@Override
	public String convert(final RSVP rsvp) {
		String result;

		if (rsvp == null)
			result = null;
		else
			result = String.valueOf(rsvp.getId());

		return result;
	}

}
