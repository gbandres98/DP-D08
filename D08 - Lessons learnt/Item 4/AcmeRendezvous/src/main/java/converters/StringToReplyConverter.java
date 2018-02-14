
package converters;

/*
 * import javax.transaction.Transactional;
 * 
 * import org.apache.commons.lang.StringUtils;
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.core.convert.converter.Converter;
 * import org.springframework.stereotype.Component;
 * 
 * import repositories.ReplyRepository;
 * import domain.Reply;
 * 
 * @Component
 * 
 * @Transactional
 * public class StringToReplyConverter implements Converter<String, Reply> {
 * 
 * @Autowired
 * private ReplyRepository replyRepository;
 * 
 * 
 * @Override
 * public Reply convert(final String text) {
 * Reply result;
 * int id;
 * 
 * try {
 * if (StringUtils.isEmpty(text))
 * result = null;
 * else {
 * id = Integer.valueOf(text);
 * result = this.replyRepository.findOne(id);
 * }
 * } catch (final Throwable oops) {
 * throw new IllegalArgumentException(oops);
 * }
 * return result;
 * }
 * }
 */
