package exam.reece.addressBook.dao;

/**
 * The Interface of Data Access Object for AdderssBook.
 * 
 * @author  Rui Zhou
 * @see 	exam.reece.addressBook.model.AdderssBook
 */
import java.util.List;

import exam.reece.addressBook.dao.exception.DaoException;
import exam.reece.addressBook.dao.exception.NotExistsException;
import exam.reece.addressBook.model.AddressBook;

public interface AddressBookDao {

	/**
	 * find all contacts by the id of owner.
	 * 
	 * @param ownerId
	 * @return 
	 */
	List<AddressBook> findByOwnerId(String ownerId);

	/**
	 * Save addrBook object to persistence store.
	 * 
	 * @param addrBook
	 * @return
	 * @throws DaoException
	 */
	AddressBook create(AddressBook addrBook) throws DaoException;

	/**
	 * Remove all the address book items from persistence store.
	 * 
	 */
	void clear();

	/**
	 * Load a AddressBook entity object form persistence by ownerId and address book id.
	 * 
	 * @param ownerId
	 * @param id
	 * @return
	 * @throws NotExistsException
	 */
	AddressBook read(String ownerId, String id) throws NotExistsException;

	/**
	 * Update the name of the address book defined by owner and id.
	 * 
	 * @param ownerId
	 * @param addrBookId
	 * @param newName
	 * @throws DaoException
	 */
	void updateName(String ownerId, String addrBookId, String newName) throws DaoException;

	/**
	 * Delete special address book defined by owner and id
	 * 
	 * @param ownerId
	 * @param id
	 * @throws DaoException
	 */
	void delete(String ownerId, String id) throws DaoException;

}
