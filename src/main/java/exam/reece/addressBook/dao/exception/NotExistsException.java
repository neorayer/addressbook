package exam.reece.addressBook.dao.exception;


/**
 * NotExistsException will be thrown when you try to read or delete a no-existing entity.
 * 
 * @author  Rui Zhou
 * @see     java.lang.Exception
 */
public class NotExistsException extends DaoException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 905782440390493819L;

	public NotExistsException() {
		super("No such dao");
	}

}
