
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.GPSCoordinates;

@Component
@Transactional
public class GPSCoordinatesToStringConverter implements Converter<GPSCoordinates, String> {

	@Override
	public String convert(final GPSCoordinates gpsCoordinates) {
		String result;

		if (gpsCoordinates == null)
			result = null;
		else
			result = String.valueOf(gpsCoordinates.getId());

		return result;
	}

}
