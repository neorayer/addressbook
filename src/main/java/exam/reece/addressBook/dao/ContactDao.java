package exam.reece.addressBook.dao;

/**
 * The Interface of Data Access Object for Contact.
 * 
 * @author  Rui Zhou
 * @see 	exam.reece.addressBook.model.Contact
 */
import java.util.List;

import exam.reece.addressBook.dao.exception.NotExistsException;
import exam.reece.addressBook.model.*;

/**
 * The Interface of Data Access Object for Contact.
 */
public interface ContactDao {

	/**
	 * Create a contact object entity into persistent store.
	 * 
	 * @param contact
	 * @return
	 */
	Contact create(Contact contact);

	/**
	 * Load a contact object from persistent store with special id.
	 * 
	 * @param contactId
	 * @return
	 * @throws NotExistsException
	 */
	Contact readById(String contactId) throws NotExistsException;

	/**
	 * Find all contacts of a special address book defined by id.
	 * 
	 * @param id
	 * @return
	 */
	List<Contact> findByAddressBookId(String id);

	/**
	 * Delete a special contact object from persistence store.
	 * 
	 * @param contactId
	 * @throws NotExistsException
	 */
	void deleteById(String contactId) throws NotExistsException;

	/**
	 * Find all contacts of a special address book
	 * 
	 * @param ab
	 * @return
	 */
	List<Contact> find(AddressBook ab);

}
