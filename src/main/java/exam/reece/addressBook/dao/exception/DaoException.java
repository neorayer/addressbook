package exam.reece.addressBook.dao.exception;

/**
 * The class {@code DaoException} should be thrown by Data Access Object(DAO).
 * It's the base class of other exceptions.
 * 
 * @author   Rui Zhou
 * @see     java.lang.Exception
 */
public class DaoException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DaoException(String msg) {
		super(msg);
	}

}
