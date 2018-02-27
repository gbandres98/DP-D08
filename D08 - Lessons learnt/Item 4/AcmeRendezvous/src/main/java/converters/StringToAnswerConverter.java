
package converters;

import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import repositories.AnswerRepository;
import domain.Answer;

@Component
@Transactional
public class StringToAnswerConverter implements Converter<String, Answer> {

	@Autowired
	private AnswerRepository	answerRepository;


	@Override
	public Answer convert(final String text) {
		Answer result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = this.answerRepository.findOne(id);
			}
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}
		return result;
	}
}
