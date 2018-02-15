
package converters;

import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import repositories.RendezvousRepository;
import domain.Rendezvous;

@Component
@Transactional
public class StringToRendezvousConverter implements Converter<String, Rendezvous> {

	@Autowired
	private RendezvousRepository	rendezvousRepository;


	@Override
	public Rendezvous convert(final String text) {
		Rendezvous result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = this.rendezvousRepository.findOne(id);
			}
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}
		return result;
	}
}
