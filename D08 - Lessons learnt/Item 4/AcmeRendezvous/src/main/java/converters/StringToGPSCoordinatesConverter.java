
package converters;

import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import repositories.GPSCoordinatesRepository;
import domain.GPSCoordinates;

@Component
@Transactional
public class StringToGPSCoordinatesConverter implements Converter<String, GPSCoordinates> {

	@Autowired
	private GPSCoordinatesRepository	gpsCoordinatesRepository;


	@Override
	public GPSCoordinates convert(final String text) {
		GPSCoordinates result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = this.gpsCoordinatesRepository.findOne(id);
			}
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}
		return result;
	}
}
