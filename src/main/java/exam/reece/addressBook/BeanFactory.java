package exam.reece.addressBook;


/**
 * Very important interface to define the IoC rule in this application.
 * It could be refined if some framework is involved in the future. eg. spring. 
 * 
 * @author   Rui Zhou
 * @see     java.lang.Exception
 * @see     exam.reece.addressBook.BeanFactory;
 */
public interface BeanFactory {
	public <T> T getBean(String beanName) throws BeanException;

}
