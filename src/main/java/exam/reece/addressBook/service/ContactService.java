package exam.reece.addressBook.service;

/**
 * Service Object for contact including main business logic.
 * It could be enhanced by Transaction and Logger while some framework involved.
 * 
 * @author  Rui Zhou
 */
import exam.reece.addressBook.model.*;

import java.util.ArrayList;
import java.util.List;

import exam.reece.addressBook.dao.*;
import exam.reece.addressBook.dao.exception.NotExistsException;

public class ContactService {

	private ContactDao contactDao;
	
	private AddressBookService addressBookService;

	public ContactDao getContactDao() {
		return contactDao;
	}

	public void setContactDao(ContactDao contactDao) {
		this.contactDao = contactDao;
	}

	public AddressBookService getAddressBookService() {
		return addressBookService;
	}

	public void setAddressBookService(AddressBookService addressBookService) {
		this.addressBookService = addressBookService;
	}

	/**
	 * Add a contact to special address book.
	 * 
	 * @param addrBook
	 * @param contact
	 * @return
	 */
	public Contact addContact(AddressBook addrBook, Contact contact) {
		contact.setAddressBookId(addrBook.getId());
		return contactDao.create(contact);
	}

	/**
	 * Get a special contact object by id.
	 * 
	 * @param contactId
	 * @return
	 * @throws NotExistsException
	 */
	public Contact getContactById(String contactId) throws NotExistsException {
		return contactDao.readById(contactId);
	}

	/**
	 * Get all contacts of an address book.
	 * 
	 * @param addrBook
	 * @return
	 */
	public List<Contact> getContacts(AddressBook addrBook) {
		return contactDao.findByAddressBookId(addrBook.getId());
	}

	/**
	 * Remove a special contact by its id.
	 * 
	 * @param contactId
	 * @throws NotExistsException
	 */
	public void deleteContact(String contactId) throws NotExistsException {
		contactDao.deleteById(contactId);

	}

	/**
	 * Get all contacts of all address books of the special owner.
	 * 
	 * @param ownerId
	 * @return
	 */
	public List<Contact> getAllContactsOfAllBooks(String ownerId) {
		List<Contact> contacts = new ArrayList<Contact>();
		
		List<AddressBook> addrBooks = addressBookService.getAddrbooksOfOwner(ownerId);
		for(AddressBook ab: addrBooks) {
			contacts.addAll(contactDao.find(ab));
		}
		return contacts;
	}

}
