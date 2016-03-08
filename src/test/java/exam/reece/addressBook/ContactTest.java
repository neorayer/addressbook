package exam.reece.addressBook;

import java.util.List;

import exam.reece.addressBook.dao.exception.DaoException;
import exam.reece.addressBook.dao.exception.NotExistsException;
import exam.reece.addressBook.model.*;
import exam.reece.addressBook.service.*;
import junit.framework.TestCase;

public class ContactTest extends TestCase {
	private BeanFactory beanFactory = new SimpleBeanFactory();

	private AddressBookService addrBookService;

	private ContactService contactService;

	private String ownerId = "U000123";

	private AddressBook addrBook1;
	private AddressBook addrBook2;
	private AddressBook addrBook3;

	protected void setUp() throws Exception {
		contactService = beanFactory.getBean("ContactService");
		addrBookService = beanFactory.getBean("AddressBookService");

		// clear db and recreate a new address book before every test.
		addrBookService.deleteAll();
		addrBook1 = addrBookService.create(ownerId, "Friends And Families");
		addrBook2 = addrBookService.create(ownerId, "Workmates");
		addrBook3 = addrBookService.create(ownerId, "Clients");
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/**
	 * Users should be able to add new contact entries
	 * 
	 * @throws NotExistsException
	 */
	public void testAddContact() throws NotExistsException {
		Contact contact = new Contact();
		contact.setName("Jack Sparrow");
		contact.setMobilePhone("0438200300");
		contact.setHomePhone("03 12345678");
		contact.setWorkPhone("03 98765432");

		// create
		String newContactId = contactService.addContact(addrBook1, contact).getId();

		// read
		contact = contactService.getContactById(newContactId);

		// assert
		assertEquals(1, contactService.getContacts(addrBook1).size());
		assertEquals("Jack Sparrow", contact.getName());
		assertEquals("0438200300", contact.getMobilePhone());
		assertEquals("03 12345678", contact.getHomePhone());
		assertEquals("03 98765432", contact.getWorkPhone());
	}

	/**
	 * Users should be able to remove existing contact entries
	 * 
	 * @throws DaoException
	 */
	public void testRemoveContact() throws DaoException {
		Contact contact = new Contact();
		contact.setName("Jack Sparrow");
		// create
		String contactId = contactService.addContact(addrBook1, contact).getId();
		// assert
		assertEquals(1, contactService.getContacts(addrBook1).size());
		// remove
		contactService.deleteContact(contactId);
		// assert
		assertEquals(0, contactService.getContacts(addrBook1).size());

	}

	/**
	 * Users should be able to print a unique set of all contacts across
	 * multiple address books
	 */
	public void tesPrintAllContactsOfAllBooks() {
		// add 1 contacts to addrbook1
		contactService.addContact(addrBook1, new Contact() {
			{
				setName("Jack Sparrow");
			}
		});

		// add 2 contacts to addrbook2
		contactService.addContact(addrBook2, new Contact() {
			{
				setName("Hulk");
			}
		});
		contactService.addContact(addrBook2, new Contact() {
			{
				setName("American Captain");
			}
		});
		
		// add 3 contacts to addrbook3
		contactService.addContact(addrBook3, new Contact() {
			{
				setName("IronMan");
			}
		});
		contactService.addContact(addrBook3, new Contact() {
			{
				setName("SpideMan");
			}
		});
		contactService.addContact(addrBook3, new Contact() {
			{
				setName("AntMan");
			}
		});
		
		List<Contact> contacts = contactService.getAllContactsOfAllBooks(ownerId);
		assertEquals(6, contacts.size());
	}

}
