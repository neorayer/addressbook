package exam.reece.addressBook.service;


/**
 * Service Object for address book including main business logic.
 * It could be enhanced by Transaction and Logger while some framework involved.
 * 
 * @author  Rui Zhou
 */
import java.util.List;
import exam.reece.addressBook.dao.AddressBookDao;
import exam.reece.addressBook.dao.exception.DaoException;
import exam.reece.addressBook.dao.exception.NotExistsException;
import exam.reece.addressBook.model.*;

public class AddressBookService {

	private AddressBookDao addressBookDao;

	public AddressBookDao getAddressBookDao() {
		return addressBookDao;
	}

	public void setAddressBookDao(AddressBookDao addressBookDao) {
		this.addressBookDao = addressBookDao;
	}

	/**
	 * Create an addressbook.
	 * 
	 * @param ownerId
	 * @param addrBookName
	 * @return
	 * @throws DaoException
	 */
	public AddressBook create(String ownerId, String addrBookName) throws DaoException {
		AddressBook addrBook = new AddressBook();
		addrBook.setOwnerId(ownerId);
		addrBook.setName(addrBookName);

		return addressBookDao.create(addrBook);
	}

	/**
	 * Get the address book of special owner.
	 * 
	 * @param ownerId
	 * @return
	 */
	public List<AddressBook> getAddrbooksOfOwner(String ownerId) {
		return addressBookDao.findByOwnerId(ownerId);
	}

	/**
	 * delete all address books.
	 * Warn!! very danger, should be remove in production environment.
	 */
	public void deleteAll() {
		addressBookDao.clear();
	}

	/**
	 * Get a special address book object by special owner and id.
	 * 
	 * @param ownerId
	 * @param id
	 * @return
	 * @throws NotExistsException
	 */
	public AddressBook getAddressBook(String ownerId, String id) throws NotExistsException {
		return addressBookDao.read(ownerId, id);
	}

	/**
	 * Update the name of a address book.
	 * 
	 * @param ownerId
	 * @param addrBookId
	 * @param newName
	 * @throws DaoException
	 */
	public void updateAddressBookName(String ownerId, String addrBookId, String newName) throws DaoException {
		addressBookDao.updateName(ownerId, addrBookId, newName);
	}

	/**
	 * Delete a special address book
	 * 
	 * @param book
	 * @throws DaoException
	 */
	public void deleteAddressBook(AddressBook book) throws DaoException {
		addressBookDao.delete(book.getOwnerId(), book.getId());
	}

}
