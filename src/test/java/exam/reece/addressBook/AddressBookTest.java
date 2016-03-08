package exam.reece.addressBook;

import java.util.Collection;
import java.util.List;


import exam.reece.addressBook.dao.exception.DaoException;
import exam.reece.addressBook.model.AddressBook;
import exam.reece.addressBook.service.AddressBookService;
import junit.framework.TestCase;

public class AddressBookTest extends TestCase {

	private BeanFactory beanFactory = new SimpleBeanFactory();

	private AddressBookService service;

	private String ownerId = "U000123";

	// private EntityManagerFactory entityManagerFactory;
	//
	protected void setUp() throws Exception {
		service = beanFactory.getBean("AddressBookService");
		service.deleteAll();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/**
	 * Users should be able to create address book
	 * 
	 * @throws Exception
	 */
	public void testCreateAddressBook() throws Exception {
		AddressBookService service = beanFactory.getBean("AddressBookService");
		// Create
		service.create(ownerId, "Friends");
		// Read
		List<AddressBook> addrBooks = service.getAddrbooksOfOwner(ownerId);

		// Check - size
		assertEquals(1, addrBooks.size());

		// Check - ownerId & id
		assertEquals(ownerId, addrBooks.get(0).getOwnerId());
		assertEquals("Friends", addrBooks.get(0).getName());
	}

	/**
	 * Users should be able to update existing address book
	 * 
	 * @throws DaoException
	 */
	public void testReadAddressBook() throws DaoException {
		// Initial create
		String addrBookId = service.create(ownerId, "Friends").getId();

		// Read
		AddressBook addrBook = service.getAddressBook(ownerId, addrBookId);

		// Check
		assertEquals("Friends", addrBook.getName());
		assertEquals(ownerId, addrBook.getOwnerId());
	}

	/**
	 * Users should be able to update existing address book
	 * 
	 * @throws DaoException
	 */
	public void testUpdateAddressBook() throws DaoException {
		// Initial create
		String addrBookId = service.create(ownerId, "Friends").getId();
		// Update
		service.updateAddressBookName(ownerId, addrBookId, "Friends And Families");
		// Read
		AddressBook addrBook = service.getAddressBook(ownerId, addrBookId);

		// Check
		assertEquals("Friends And Families", addrBook.getName());
		assertEquals(ownerId, addrBook.getOwnerId());
	}

	/**
	 * Users should be able to delete existing address book
	 * 
	 * @throws DaoException
	 */
	public void testDeleteAddressBook() throws DaoException {
		// Initial create 2 books
		AddressBook book1 = service.create(ownerId, "Friends");
		AddressBook book2 = service.create(ownerId, "Workmates");

		List<AddressBook> books = service.getAddrbooksOfOwner(ownerId);
		assertEquals(2, books.size());

		// delete book1
		service.deleteAddressBook(book1);
		assertEquals(1, books.size());
		assertEquals("Workmates", books.get(0).getName());

		// delete book2
		service.deleteAddressBook(book2);
		assertEquals(0, books.size());

	}

	/**
	 * Users should be able to maintain multiple address books
	 * 
	 * @throws DaoException
	 */
	public void testMultiAdderssBooks() throws DaoException {
		// Create
		service.create(ownerId, "Friends");
		service.create(ownerId, "Workmates");
		service.create(ownerId, "Clients");

		// Read
		List<AddressBook> addrBooks = service.getAddrbooksOfOwner(ownerId);

		// Check - size
		assertEquals(3, addrBooks.size());

		// Check - ownerId
		assertEquals(ownerId, addrBooks.get(0).getOwnerId());
		assertEquals(ownerId, addrBooks.get(1).getOwnerId());
		assertEquals(ownerId, addrBooks.get(2).getOwnerId());
		assertEquals("Friends", addrBooks.get(0).getName());
		assertEquals("Workmates", addrBooks.get(1).getName());
		assertEquals("Clients", addrBooks.get(2).getName());
	}



}
