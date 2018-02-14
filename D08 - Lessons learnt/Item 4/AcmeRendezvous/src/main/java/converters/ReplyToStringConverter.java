
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Reply;

@Component
@Transactional
public class ReplyToStringConverter implements Converter<Reply, String> {

	@Override
	public String convert(final Reply reply) {
		String result;

		if (reply == null)
			result = null;
		else
			result = String.valueOf(reply.getId());

		return result;
	}

}
