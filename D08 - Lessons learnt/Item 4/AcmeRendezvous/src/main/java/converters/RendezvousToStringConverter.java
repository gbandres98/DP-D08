
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Rendezvous;

@Component
@Transactional
public class RendezvousToStringConverter implements Converter<Rendezvous, String> {

	@Override
	public String convert(final Rendezvous rendezvous) {
		String result;

		if (rendezvous == null)
			result = null;
		else
			result = String.valueOf(rendezvous.getId());

		return result;
	}

}
