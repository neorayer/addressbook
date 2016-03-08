package exam.reece.addressBook.dao.simpleImpl;

/**
 * The most simple implementation to simulate a persistence store. 
 * Java 8 is required for the Lambda involved.
 * 
 * @author  Rui Zhou
 * @see 	exam.reece.addressBook.dao.ContactDao
 */
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import exam.reece.addressBook.dao.*;
import exam.reece.addressBook.dao.exception.NotExistsException;
import exam.reece.addressBook.model.*;

public class SimpleContactDao implements ContactDao {
	/**
	 * A static list to simulator of persistence store for Contact.
	 */
	private static List<Contact> contacts = new ArrayList<Contact>();

	@Override
	public Contact create(Contact contact) {
		contacts.add(contact);
		return contact;
	}

	@Override
	public Contact readById(String contactId) throws NotExistsException {
		for (Contact c : contacts.toArray(new Contact[0])) {
			if (c.getId().equals(contactId))
				return c;
		}
		throw new NotExistsException();
	}

	@Override
	public List<Contact> findByAddressBookId(String id) {
		return contacts.stream().filter(c -> c.getAddressBookId().equals(id)).collect(Collectors.toList());
	}

	@Override
	public List<Contact> find(AddressBook ab) {
		return findByAddressBookId(ab.getId());
	}

	@Override
	public void deleteById(String contactId) throws NotExistsException {
		Contact c = readById(contactId);
		contacts.remove(c);
	}

}
