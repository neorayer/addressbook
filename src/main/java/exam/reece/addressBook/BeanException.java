package exam.reece.addressBook;


/**
 * The class {@code BeanException} should be thrown by BeanFactory and its sub class.
 * 
 * @author   Rui Zhou
 * @see     java.lang.Exception
 * @see     exam.reece.addressBook.BeanFactory;
 */
public class BeanException extends Exception{

	public BeanException(Exception e) {
		super(e);
	}

	public BeanException(String msg) {
		super(msg);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -5230218193974526526L;

}
